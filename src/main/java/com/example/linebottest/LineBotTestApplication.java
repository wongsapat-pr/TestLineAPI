package com.example.linebottest;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.activation.FileTypeMap;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.objectmapper.ModelObjectMapper;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.model.richmenu.RichMenu;
import com.linecorp.bot.model.richmenu.RichMenuIdResponse;
import com.linecorp.bot.model.richmenu.RichMenuListResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.yaml.snakeyaml.Yaml;

import lombok.extern.slf4j.Slf4j;
import com.google.common.cache.LoadingCache;

@Slf4j
@SpringBootApplication
public class LineBotTestApplication {
	public static Path downloadedContentDir;
	// private static String token = "pDRdwcmHqqNjTMdnUmzDW1qQ7eW3tYX2+szzEJmyweXce6RCQL5HFaE/R2nHo0Gj3HfIfr2ZzXndQJn5NJBg1AGpwxJ3RmKi5ChOebHhmvHzz+Ycijsjugyo4biyYPiTxxhbuJ7XNXaGV57RoxOW+wdB04t89/1O/w1cDnyilFU="; // Your LINE token
    // private static LineMessagingClient lineMessagingClient = 
    //                         LineMessagingClient.builder(token).build();

	public static void main(String[] args) throws IOException {
		downloadedContentDir = Files.createTempDirectory("line-bot");
		// //YAML
        // String pathYamlHome = "asset/richmenu-home.yml";
        // String pathYamlMore = "asset/richmenu-more.yml";
        // // Rich Image Menu
        // String pathImageHome = "asset/richmenu-home.jpg";
        // String pathImageMore = "asset/richmenu-more.jpg";
      
        // String richMenuId;

        // // Create 1st Rich Menu (Home Menu)
        // richMenuId = createRichMenu(pathYamlHome);
        // imageUploadRichMenu(richMenuId, pathImageHome);

        // // Create 2nd Rich Menu (More Menu)
        // richMenuId = createRichMenu(pathYamlMore);
        // imageUploadRichMenu(richMenuId, pathImageMore);

        // listRichMenu(); // Show created Rich Menus
		
		SpringApplication.run(LineBotTestApplication.class, args);
	}

	// private static String createRichMenu(String path) throws IOException {
    //     RichMenu richMenu = loadYaml(path);
    //     log.info("{}", richMenu);

    //     RichMenuIdResponse richMenuResponse = getUnchecked(
    //                                 lineMessagingClient.createRichMenu(richMenu));
    //     log.info("Successfully finished.");
    //     log.info("{}", richMenuResponse);
    //     return richMenuResponse.getRichMenuId();
    // }

    // private static RichMenuIdResponse getUnchecked(CompletableFuture<RichMenuIdResponse> createRichMenu) {
	// 	return null;
	// }

	// private static void imageUploadRichMenu(String richMenuId, String path) throws IOException {
    //     String contentType = FileTypeMap.getDefaultFileTypeMap().getContentType(path);
    //     log.info("Content-Type: {}", contentType);

    //     byte[] bytes = Files.readAllBytes(Paths.get(path));

    //     BotApiResponse botApiResponse = getUnchecked1(
    //                                 lineMessagingClient.setRichMenuImage(
    //                                     richMenuId, contentType, bytes));
    //     log.info("Successfully finished.");
    //     log.info("{}", botApiResponse);
    // }
  
    // private static BotApiResponse getUnchecked1(CompletableFuture<BotApiResponse> setRichMenuImage) {
	// 	return null;
	// }

	// private static List<String> listRichMenu() {
    //     List<String> listMenuString = new ArrayList<>();

    //     RichMenuListResponse richMenuListResponse = getUnchecked2(lineMessagingClient.getRichMenuList());
    //     List<com.linecorp.bot.model.richmenu.RichMenuResponse> listMenus = richMenuListResponse.getRichMenus();
    //     log.info("You have {} RichMenus", listMenus.size());

    //     log.info("Successfully finished.");
    //     listMenus.forEach(richMenuResponse -> {
    //         listMenuString.add(richMenuResponse.getRichMenuId());
    //         log.info("{}", richMenuResponse);
    //     });

    //     return listMenuString;
    // }

    // private static RichMenuListResponse getUnchecked2(CompletableFuture<RichMenuListResponse> richMenuList) {
	// 	return null;
	// }

	// private static RichMenu loadYaml(String path) throws IOException {
    //     final Yaml YAML = new Yaml();
    //     final ObjectMapper OBJECT_MAPPER = ModelObjectMapper
    //             .createNewObjectMapper()
    //             .configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
    //             .configure(Feature.ALLOW_COMMENTS, true)
    //             .configure(Feature.ALLOW_SINGLE_QUOTES, true)
    //             .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
    //             .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)
    //             .configure(SerializationFeature.INDENT_OUTPUT, true);

    //     Object yamlAsObject;
    //     try(FileInputStream is = new FileInputStream(path)) {
    //         yamlAsObject = YAML.load(is);
    //     }

    //     return OBJECT_MAPPER.convertValue(yamlAsObject, RichMenu.class);
    // }
}
