package com.zd.chat.controller;

import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import com.zd.chat.models.prompt.PromptEvaluationPanelResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * 提示词生成
 */
@Slf4j
@RestController
@RequestMapping("generate")
public class PromptGenerateController {

    private final OllamaChatModel ollamaChatModel;

    private final OpenAiChatModel openAiChatModel;

    private final ApplicationContext applicationContext;

    public PromptGenerateController(OllamaChatModel ollamaChatModel, OpenAiChatModel openAiChatModel,
                                    ApplicationContext applicationContext) {
        this.ollamaChatModel = ollamaChatModel;
        this.openAiChatModel = openAiChatModel;
        this.applicationContext = applicationContext;
    }

    private ChatModel getChatModel(String model) {
        return "openai".equals(model) ? openAiChatModel : ollamaChatModel;
    }

    @GetMapping
    public ResponseEntity<String> generate(String prompt) throws IOException {
        String analyzerResponse = promptAnalyzer(prompt);
        String generate = generate(analyzerResponse, 0, new ArrayList<>());
        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.parseMediaType("text/markdown; charset=utf-8"));

        // 返回带有响应头的响应
        return new ResponseEntity<>(generate, headers, HttpStatus.OK);
    }

    public String generate(String prompt, int count, List<AssistantMessage> messages) throws IOException {
        String promptOptimizer = promptOptimizer(prompt, messages);
        String promptEnhancer = promptEnhancer(promptOptimizer, messages);
        PromptEvaluationPanelResponse panelResponse = promptEvaluationPanel(promptEnhancer, messages);
        //判断生成的评分
        if (panelResponse.isOk() || count >= 5) {
            //预测优化后能通过，生成最后一次
            return promptOptimizer(prompt, messages);
        } else {
            log.warn("失败次数:{}", ++count);
            //失败了,重新生成提示词并再次强化
            return generate(prompt, count, messages);
        }
    }

    /**
     * 多个角度批判性地分析和评估优化的提示
     *
     * @param userMessage 提示
     * @return 多个角度批判性地分析和评估优化的提示
     * @throws IOException 读取异常
     */
    private PromptEvaluationPanelResponse promptEvaluationPanel(String userMessage, List<AssistantMessage> messages) throws IOException {
        String content = chat("classpath:prompt/PromptEvaluationPanel.st", userMessage, messages);
        log.info("多个角度批判性地分析和评估优化的提示: {}", content);
        if (content.startsWith("```")) {
            content = content.replaceAll("```", "");
            content = content.replaceAll("json", "");
        }
        return JSONUtil.toBean(content, new JSONConfig().setIgnoreCase(true), PromptEvaluationPanelResponse.class);
    }

    /**
     * 预测与给定提示交互时的潜在问题、限制和用户行为
     *
     * @param localizationAdapter 提示词
     * @return 预测与给定提示交互时的潜在问题、限制和用户行为
     * @throws IOException 读取异常
     */
    private String interactionSimulator(String localizationAdapter) throws IOException {
        Resource resource = applicationContext.getResource("classpath:prompt/LocalizationAdapter.st");
        SystemMessage assistantMessage = new SystemMessage(resource.getContentAsString(Charset.defaultCharset()));
        return getChatModel("").call(new Prompt(List.of(assistantMessage, new UserMessage(localizationAdapter)))).getResult().getOutput().getContent();
    }

    /**
     * 文化敏感度优化
     *
     * @param promptEnhancer 提示词增强结果
     * @return 文化敏感度优化
     */
    private String localizationAdapter(String promptEnhancer) throws IOException {
        Resource resource = applicationContext.getResource("classpath:prompt/LocalizationAdapter.st");
        SystemMessage assistantMessage = new SystemMessage(resource.getContentAsString(Charset.defaultCharset()));
        return getChatModel("").call(new Prompt(List.of(assistantMessage, new UserMessage(promptEnhancer)))).getResult().getOutput().getContent();
    }

    /**
     * 提示词增强
     *
     * @param userMessage 提示词强化结果
     * @return 提示词增强结果
     * @throws IOException 读取异常
     */
    private String promptEnhancer(String userMessage, List<AssistantMessage> messages) throws IOException {
        String content = chat("classpath:prompt/PromptEnhancer.st", userMessage, messages);
        log.info("提示词增强：{}", content);
        return content;
    }

    /**
     * 提示词强化
     *
     * @param userMessage 提示词分析结果
     * @param messages
     * @return 提示词强化结果
     * @throws IOException 读取异常
     */
    private String promptOptimizer(String userMessage, List<AssistantMessage> messages) throws IOException {
        String content = chat("classpath:prompt/PromptOptimizer.st", userMessage, messages);
        log.info("提示词生成: {}", content);
        return content;
    }

    /**
     * 提示词分析
     *
     * @param userMessage 提示词
     * @return 提示词分析结果
     * @throws IOException 读取异常
     */
    private String promptAnalyzer(String userMessage) throws IOException {
        String content = chat("classpath:prompt/PromptAnalyzer.st", userMessage, new ArrayList<>());
        log.info("提示词分析结果：{}", content);
        return content;
    }

    private String chat(String promptFile, String userMessage, List<AssistantMessage> assistantMessages) throws IOException {
        Resource resource = applicationContext.getResource(promptFile);
        SystemMessage systemMessage = new SystemMessage(resource.getContentAsString(Charset.defaultCharset()));
        List<Message> messageList = new ArrayList<>(List.of(systemMessage, new UserMessage(userMessage)));
        messageList.addAll(assistantMessages);
        String content = getChatModel("openai").call(new Prompt(messageList)).getResult().getOutput().getContent();
        messageList.add(new AssistantMessage(content));
        return content;
    }
}
