package info.vitdo82.spring.web;

import com.google.common.collect.Lists;
import info.vitdo82.spring.web.config.SecurityConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by vit on 3/4/16.
 */
@SpringBootApplication
@ComponentScan(basePackages = "info.vitdo82.spring.web")
@EnableSwagger2
public class Application extends SpringBootServletInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api").apiInfo(apiInfo())
                .securitySchemes(Lists.newArrayList(apiKey()))
                .select().paths(PathSelectors.regex("/api.*")).build();
    }

    private ApiKey apiKey() {
        return new ApiKey(SecurityConfig.AUTHORIZATION_HEADER, SecurityConfig.AUTHORIZATION_HEADER, "header");
    }

    @Bean
    public SecurityConfiguration security() {
        return new SecurityConfiguration(
                "my-app-client-id", "my-app-client-secret",
                "my-app-realm", "my-app",
                null, ApiKeyVehicle.HEADER,
                SecurityConfig.AUTHORIZATION_HEADER, ",");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("My REST").description("My REST with Swagger").build();
    }
}
