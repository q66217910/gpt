package com.zd.chat.models.prompt;

import com.zd.chat.models.prompt.evaluation.EvaluationScore;
import com.zd.chat.models.prompt.evaluation.OverallAssessment;
import lombok.Data;

@Data
public class PromptEvaluationPanelResponse {

    private EvaluationScore clarityAndSpecificity;

    private EvaluationScore taskAlignment;

    private EvaluationScore languageModelOptimization;

    private EvaluationScore creativityAndInnovation;

    private OverallAssessment overallAssessment;

    private Object futureConsiderations;

    public boolean isOk() {
        return this.clarityAndSpecificity.getScore() >= 7
                && this.taskAlignment.getScore() >= 7
                && this.languageModelOptimization.getScore() >= 7
                && this.creativityAndInnovation.getScore() >= 7
                && this.overallAssessment.getAverageScore() >= 7;
    }
}
