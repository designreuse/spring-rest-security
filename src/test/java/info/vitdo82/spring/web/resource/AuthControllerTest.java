package info.vitdo82.spring.web.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.vitdo82.spring.web.Application;
import info.vitdo82.spring.web.resource.dto.LoginModel;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;

/**
 * Created by vit on 4/4/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class AuthControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthControllerTest.class);

    @Autowired
    WebApplicationContext context;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @InjectMocks
    AuthController controller;

    private MockMvc mvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).addFilter(springSecurityFilterChain).build();
    }

    @Test
    public void testAuthorize() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        LoginModel loginModel = new LoginModel();
        loginModel.setUserName("test");
        loginModel.setPassword("test");
        loginModel.setRememberMe(true);

        String content = mvc.perform(MockMvcRequestBuilders.post("/api/authenticate")
                .content(objectMapper.writeValueAsString(loginModel))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.authorization", Matchers.any(String.class)))
                .andReturn().getResponse().getContentAsString();

        HashMap response = objectMapper.readValue(content, HashMap.class);
        LOGGER.debug("Authorization : {}", response.get("authorization"));
    }
}