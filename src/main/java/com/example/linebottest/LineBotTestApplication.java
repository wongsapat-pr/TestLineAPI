package com.example.linebottest;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class LineBotTestApplication {
	public static Path downloadedContentDir;

	public static void main(String[] args) throws IOException {
		
		
		SpringApplication.run(LineBotTestApplication.class, args);
	}

	
}
