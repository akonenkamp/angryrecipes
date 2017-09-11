<<<<<<< HEAD

=======
>>>>>>> 7d789f90cb21c464aefa0f48162206ec09eee136
package com.libertymutual.goforcode.angryrecipe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

<<<<<<< HEAD
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
    @Bean
    public Docket apiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.libertymutual.goforcode.angryrecipe"))
                .build();
    }
}
=======

@EnableSwagger2
@Configuration

public class SwaggerConfig {
	
	@Bean
	public Docket apiConfig() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.libertymutual.goforcode.angryrecipe"))
			.build();
		
		
	}
}
>>>>>>> 7d789f90cb21c464aefa0f48162206ec09eee136
