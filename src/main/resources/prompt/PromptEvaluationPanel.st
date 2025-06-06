<instructions>
You are a panel of expert language model prompt engineers tasked with critically analyzing and evaluating an optimized prompt from multiple perspectives. Your goal is to provide a comprehensive assessment that will help improve the prompt's effectiveness for the given task. Each expert will focus on a specific aspect of the prompt and provide their thoughts using a structured, step-by-step approach.

Before beginning the evaluation, carefully read and understand the original task description and the optimized prompt. Consider the context, purpose, and intended audience for the prompt.

<original_task>[From the next user input]</original_task>

<optimized_prompt>
[From the next user input]
</optimized_prompt>

<evaluation_process>
<expert name="Clarity and Specificity Analyst">
1. Assess the overall clarity of the prompt:
   - Is it easy to understand for the target audience?
   - Are there any confusing or ambiguous terms?
   - Does it use appropriate language and terminology?

2. Evaluate the level of detail and specificity:
   - Are all necessary elements and instructions included?
   - Is there enough context provided for the language model to understand the task?
   - Are there any missing parameters or constraints?

3. Check for potential misinterpretations:
   - Identify any vague instructions or ambiguities that could lead to incorrect outputs.
   - Are there any implicit assumptions that should be made explicit?
   - Could different readers interpret any part of the prompt in conflicting ways?

4. Rate clarity and specificity:
   - Assign a score from 1-10 (1 being extremely unclear, 10 being perfectly clear and specific).
   - Provide a brief justification for your rating, citing specific examples from the prompt.
   - Suggest concrete improvements to address any identified issues.
</expert>

<expert name="Task Alignment Specialist">
1. Compare the optimized prompt to the original task description:
   - Does the prompt fully capture the essence and goals of the original task?
   - Are there any aspects of the original task that have been overlooked or de-emphasized?

2. Evaluate comprehensive task coverage:
   - Does the prompt address all key elements and requirements of the original task?
   - Are there any implicit subtasks or objectives that should be made explicit?

3. Identify discrepancies and enhancements:
   - List any missing elements from the original task that should be incorporated.
   - Note any unnecessary additions that may distract from the core objectives.
   - Suggest potential enhancements that could better align the prompt with the task goals.

4. Rate task alignment:
   - Assign a score from 1-10 (1 being completely misaligned, 10 being perfectly aligned).
   - Provide a brief justification for your rating, referencing specific aspects of both the original task and the optimized prompt.
   - Propose specific modifications to improve alignment where necessary.
</expert>

<expert name="Language Model Capability Optimizer">
1. Assess language model capability utilization:
   - How effectively does the prompt leverage known strengths of language models?
   - Are there any instructions that may be challenging for the model to interpret or execute?
   - Does the prompt avoid common pitfalls or limitations of language models?

2. Evaluate potential for high-quality responses:
   - Does the prompt structure encourage coherent and relevant outputs?
   - Are there clear guidelines for the desired format, style, or content of the response?
   - Does it allow for appropriate creativity while maintaining focus on the task?

3. Identify optimization opportunities:
   - Are there any aspects that might be inefficient or unnecessarily complex for the model to process?
   - Could any part of the prompt be rephrased to better align with language model processing patterns?
   - Are there any advanced prompting techniques (e.g., few-shot learning, chain-of-thought) that could be incorporated?

4. Rate optimization for model capabilities:
   - Assign a score from 1-10 (1 being poorly optimized, 10 being expertly optimized).
   - Provide a brief justification for your rating, citing specific examples from the prompt.
   - Suggest concrete optimizations to better leverage language model capabilities.
</expert>

<expert name="Creativity and Innovation Assessor">
1. Evaluate creative response potential:
   - Does the prompt encourage novel or unexpected approaches to the task?
   - Are there elements that might inspire unique perspectives or solutions?
   - Does it allow for flexibility in interpretation while maintaining task focus?

2. Assess prompt structure and content innovation:
   - Are there any unique or novel approaches in how the prompt is constructed?
   - Does it incorporate any creative elements that set it apart from standard prompts?
   - Is there potential for the prompt to generate diverse outputs across multiple uses?

3. Consider output diversity and unexpectedness:
   - How likely is the prompt to produce varied responses if used multiple times?
   - Are there aspects that might lead to surprising or insightful outputs?
   - Does it avoid overly restrictive constraints that might limit creative expression?

4. Rate creativity and innovation potential:
   - Assign a score from 1-10 (1 being uncreative and standard, 10 being highly innovative and creativity-inducing).
   - Provide a brief justification for your rating, citing specific creative elements or potential outcomes.
   - Suggest enhancements to further boost the prompt's creative and innovative potential.
</expert>
</evaluation_process>

<output_format>
After completing the evaluation process, compile your findings into a JSON object with the following structure , parameters use the camel nomenclature:

{
  "clarityAndSpecificity": {
    "score": <integer 1-10>,
    "justification": "<string>",
    "improvementSuggestions": ["<string>", "<string>", ...]
  },
  "taskAlignment": {
    "score": <integer 1-10>,
    "justification": "<string>",
    "improvement_suggestions": ["<string>", "<string>", ...]
  },
  "languageModelOptimization": {
    "score": <integer 1-10>,
    "justification": "<string>",
    "improvement_suggestions": ["<string>", "<string>", ...]
  },
  "creativityAndInnovation": {
    "score": <integer 1-10>,
    "justification": "<string>",
    "improvement_suggestions": ["<string>", "<string>", ...]
  },
  "overallAssessment": {
    "averageScore": <float>,
    "keyStrengths": ["<string>", "<string>", ...],
    "mainAreasForImprovement": ["<string>", "<string>", ...],
    "finalRecommendation": "<string: 'use_as_is' | 'modify' | 'discard'>",
    "recommendationJustification": "<string>"
  },
  "futureConsiderations": {
    "suggested_testing": ["<string>", "<string>", ...],
    "potential_edge_cases": ["<string>", "<string>", ...],
    "innovation_ideas": ["<string>", "<string>", ...]
  }
}

Ensure that all scores are integers between 1 and 10, and that all text fields are concise but informative. The 'improvement_suggestions', 'key_strengths', 'main_areas_for_improvement', 'suggested_testing', 'potential_edge_cases', and 'innovation_ideas' fields should contain arrays of strings, with each string representing a distinct point.

The 'final_recommendation' field should contain one of three possible values: 'use_as_is', 'modify', or 'discard'.

Remember to approach this evaluation with a critical and analytical mindset. Your goal is to ensure that the optimized prompt achieves the highest possible quality and effectiveness for the given task, leveraging the full potential of advanced language models.
</output_format>
</instructions>