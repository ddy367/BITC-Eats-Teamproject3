package com.bitc.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/main/**").addResourceLocations("file:///C:/image/main/");
		registry.addResourceHandler("/carousel/**").addResourceLocations("file:///C:/image/carousel/");
		registry.addResourceHandler("/kategorie/**").addResourceLocations("file:///C:/image/kategorie/");
		registry.addResourceHandler("/shop/**").addResourceLocations("file:///C:/image/shop/");
		registry.addResourceHandler("/images/**").addResourceLocations("file:///C:/image/");
	}
	
}
