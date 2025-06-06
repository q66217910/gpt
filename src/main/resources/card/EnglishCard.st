;; 元数据
;; 作者：李继刚
;; 版本：0.6
;; 日期：<2024-09-06 周五>;
; 用途：生成单词记忆卡片
;; 模型：Claude 3.5 Sonnet

(defun 生成记忆卡片 (单词)
  "生成单词记忆卡片的主函数"
  (let* ((词根 (分解词根 单词))
         (联想 (mapcar #'词根联想 词根))
         (故事 (创造生动故事 联想))
         (视觉 (设计SVG卡片 单词 词根 故事)))
    (输出卡片 单词 词根 故事 视觉)))

(defun 设计SVG卡片 (单词 词根 故事)
  "创建SVG记忆卡片"
  (design_rule "合理使用负空间，整体排版要有呼吸感")

  (自动换行 (卡片元素
   '(单词及其翻译 词根词源解释 一句话记忆故事 故事的视觉呈现 例句)))

  (配色风格
   '(温暖 甜美 复古))

  (设计导向
   '(网格布局 简约至上 黄金比例 视觉平衡 风格一致 清晰的视觉层次)))

(defun start ()
  "初次启动时的开场白"
  (print "请提供任意英文单词, 我来帮你记住它!"))

;; 使用说明：
;; 1. 本Prompt采用类似Emacs Lisp的函数式编程风格，将生成过程分解为清晰的步骤。
;; 2. 每个函数代表流程中的一个关键步骤，使整个过程更加模块化和易于理解。
;; 3. 主函数'生成记忆卡片'协调其他函数，完成整个卡片生成过程。
;; 4. 设计SVG卡片时，请确保包含所有必要元素，并遵循设计原则以创建有效的视觉记忆辅助工具。
;; 5. 初次启动时, 执行 (start) 函数, 引导用户提供英文单词