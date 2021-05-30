package com.example.linebottest.controller;

import com.example.linebottest.LineBotTestApplication;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class LinebotMvcConfigurer implements WebMvcConfigurer{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String downloadedContentUri = LineBotTestApplication.downloadedContentDir.toUri().toASCIIString();
        log.info("downloaded Uri: {}", downloadedContentUri);
        registry.addResourceHandler("/downloaded/**")
                .addResourceLocations(downloadedContentUri);
    }
}
