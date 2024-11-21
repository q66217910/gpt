package com.zd.chat.models.prompt.evaluation;

import lombok.Data;

import java.util.List;

@Data
public class EvaluationScore {

    private Double score;

    private String justification;

    private List<String> improvementSuggestions;
}
