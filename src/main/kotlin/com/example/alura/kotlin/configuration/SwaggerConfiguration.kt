package com.example.alura.kotlin.configuration

import com.example.alura.kotlin.repository.orm.Usuario
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ParameterBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.schema.ModelRef
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import java.util.*

@Configuration
class SwaggerConfiguration {

    @Bean
    fun forumApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.example.alura.kotlin"))
            .paths(PathSelectors.ant("/**"))
            .build()
            .ignoredParameterTypes(Usuario::class.java)
            .globalOperationParameters(
                Collections.singletonList(
                    ParameterBuilder()
                        .name("Authorization")
                        .description("Header para token JWT")
                        .modelRef(ModelRef("string"))
                        .parameterType("header")
                        .required(false)
                        .build()
                )
            )
    }

}