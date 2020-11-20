package com.cg.healthassist.laboratory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/** This is the swagger configuration class
 * 
 * @author Sharique Nooman
 * @version 1.0
 * 
 */
@Configuration
@EnableSwagger2
public class LaboratorySwaggerConfig {

	@Bean
	public Docket laboratoryApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(metadata()).select().paths(PathSelectors.regex("/api/v1.*")).build();
	}

	/** The metadata method provides information about the API
	 * 
	 * @return ApiInfo
	 */
	private ApiInfo metadata() {
		return new ApiInfoBuilder().title("Laboratory").description("API reference for laboratory").version("1.0").build();
	}
}
