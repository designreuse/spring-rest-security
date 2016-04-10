package info.vitdo82.spring.web.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.vitdo82.spring.web.resource.dto.LoginModel;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;

/**
 * Created by vit on 4/5/16.
 */
public final class TestUtils {

    private TestUtils() {
    }

    public static String getToken(WebApplicationContext context, FilterChainProxy springSecurityFilterChain,
                                  String username, String password) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        LoginModel loginModel = new LoginModel();
        loginModel.setUserName(username);
        loginModel.setPassword(password);
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(context).addFilter(springSecurityFilterChain).build();

        String content = mvc.perform(MockMvcRequestBuilders.post("/api/authenticate")
                .content(objectMapper.writeValueAsString(loginModel))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        HashMap response = objectMapper.readValue(content, HashMap.class);

        return (String) response.get("authorization");
    }
}
