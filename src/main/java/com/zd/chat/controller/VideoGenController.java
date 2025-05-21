package com.zd.chat.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.ChatOptionsBuilder;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequestMapping("video")
public class VideoGenController {

    private final OpenAiChatModel openAiChatModel;

    private final ApplicationContext applicationContext;

    public VideoGenController(OpenAiChatModel openAiChatModel, ApplicationContext applicationContext) {
        this.openAiChatModel = openAiChatModel;
        this.applicationContext = applicationContext;
    }

    @GetMapping
    public String generate() throws IOException {
        //故事脚本
        String storyScript = storyScript();
        //根据故事脚本生成视频脚本
        return videoScript(storyScript);
    }

    /**
     * @return 故事脚本
     */
    private String storyScript() throws IOException {
        Resource resource = applicationContext.getResource("classpath:video/story.st");
        AssistantMessage assistantMessage = new AssistantMessage(resource.getContentAsString(Charset.defaultCharset()));
        return openAiChatModel.call(new Prompt(List.of(assistantMessage),
                        ChatOptionsBuilder.builder()
                                .withModel("gpt-4o")
                                .build()))
                .getResult().getOutput().getContent();
    }

    private String videoScript(String prompt) throws IOException {
        Resource resource = applicationContext.getResource("classpath:video/videoScript.st");
        AssistantMessage assistantMessage = new AssistantMessage(resource.getContentAsString(Charset.defaultCharset()));
        return openAiChatModel.call(new Prompt(List.of(assistantMessage,new UserMessage(prompt)),
                        ChatOptionsBuilder.builder()
                                .withModel("gpt-4o")
                                .build()))
                .getResult().getOutput().getContent();
    }
}
