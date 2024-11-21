package com.zd.chat.models.prompt.evaluation;

import lombok.Data;

import java.util.List;

@Data
public class OverallAssessment {

    private Double averageScore;

    private List<String> keyStrengths;

    private List<String> mainAreasForImprovement;

    private String recommendationJustification;

    private String finalRecommendation;

}
