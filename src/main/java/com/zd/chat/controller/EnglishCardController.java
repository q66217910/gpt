package com.zd.chat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.ChatOptions;
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

@Slf4j
@RestController
@RequestMapping("card")
public class EnglishCardController {

    private final OpenAiChatModel openAiChatModel;

    private final ApplicationContext applicationContext;

    public EnglishCardController(OpenAiChatModel openAiChatModel,
                                    ApplicationContext applicationContext) {
        this.openAiChatModel = openAiChatModel;
        this.applicationContext = applicationContext;
    }

    @GetMapping
    public String generate(String prompt) throws IOException {
        Resource resource = applicationContext.getResource("classpath:card/EnglishCard.st");
        AssistantMessage assistantMessage = new AssistantMessage(resource.getContentAsString(Charset.defaultCharset()));
        return openAiChatModel.call(new Prompt(List.of(assistantMessage, new UserMessage(prompt)),
                        ChatOptionsBuilder.builder()
                                .withModel("claude-3-5-sonnet")
                                .build()))
                .getResult().getOutput().getContent();
    }
}
