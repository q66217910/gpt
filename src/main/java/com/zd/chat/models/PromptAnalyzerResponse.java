package com.zd.chat.models;

import lombok.Data;

@Data
public class PromptAnalyzerResponse {

    private Object analysis;

    private Object temperature;

    private Object sampleCount;

    private Object optimizationSummary;

    private String optimizedPrompt;

}
