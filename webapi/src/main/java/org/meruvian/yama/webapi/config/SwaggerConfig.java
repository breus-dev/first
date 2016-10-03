package org.meruvian.yama.webapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import io.swagger.models.Info;

@Configuration
public class SwaggerConfig {
	@Bean
	public BeanConfig beanConfig() {
		Info info = new Info();
		info.setTitle("co");
		info.setVersion("2.0.0.Beta3-SNAPSHOT");
		
		BeanConfig config = new BeanConfig();
		config.setVersion("0.0.1-SNAPSHOT");
		config.setTitle("yama-library");
		config.setInfo(info);
//		config.setBasePath("/api");
        config.setResourcePackage("org.meruvian.yama");
		config.setScan(true);
		
		return config;
	}

	@Bean
	public ApiListingResource apiListingResource() {
		return new ApiListingResource();
	}
	
	@Bean
	public SwaggerSerializers swaggerSerializers() {
		return new SwaggerSerializers();
	}
	
//	@Bean
//	public RestCategoryService restCategoryService(){
//		return new RestCategoryService();
//	}
//	
//	@Bean
//	public RestBookService restBookService(){
//		return new RestBookService();
//	}
//	
//	@Bean
//	public RestBorrowBookService restBorrowBookService(){
//		return new RestBorrowBookService();
//	}
//	
//	@Bean
//	public RestStockbookService restStockbookService(){
//		return new RestStockbookService();
//	}
//	
//	@Bean
//	public RestHistoryBorrowBookService restHistoryBorrowBookService(){
//		return new RestHistoryBorrowBookService();
//	}
//	
//	@Bean
//	public RestReturnService restReturnService(){
//		return new RestReturnService();
//	}
}