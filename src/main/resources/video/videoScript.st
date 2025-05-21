【AI视频脚本转换指令】

请将故事脚本转换为符合抖音算法的视频分镜脚本，按以下要求生成JSON：

{
  "metadata": {
    "video_title": "包含2个热词的标题（使用##分隔）",
    "total_duration": "严格控制在28-32秒",
    "platform": "douyin",
    "tags": ["从脚本提取的3个话题标签"]
  },
  "storyboard": [
    {
      "shot_id": "递增编号",
      "time_range": ["开始秒", "结束秒"],
      "visual_elements": {
        "scene_type": "特写/中景/全景",
        "subject": "人物/物品",
        "lighting": "强对比/柔光/霓虹",
        "camera_move": "推/拉/摇/跟"
      },
      "text_elements": {
        "caption": "带悬念的短文案（≤8字）",
        "caption_style": {
          "font": "抖音热用字体",
          "color": "#HEX色值",
          "position": "顶部20%/中央/底部浮动"
        }
      },
      "audio_elements": {
        "voiceover": "浓缩后的关键台词（带情绪标记）",
        "sound_effect": "对应动作的音效名称",
        "bgm": "推荐BGM片段（精确到秒）"
      },
      "transition": {
        "type": "硬切/缩放/闪白",
        "algorithm_opt": {
          "hook_strength": 0-1值,
          "retention_score": 0-100预估
        }
      }
    }
  ],
  "algorithm_hooks": {
    "frame_hooks": [
      {
        "time": "秒数",
        "type": "悬念问题/进度条/选项浮层"
      }
    ],
    "audio_hooks": [
      {
        "time": "秒数",
        "type": "突然静音/音调骤变"
      }
    ]
  }
}

特殊要求：
1. 每5秒必须设置算法钩子，包含以下至少1项：
   - 进度条悬念提示（如"结局你绝对想不到"）
   - 互动选项浮层（如"该原谅吗？红蓝选择"）
   - 突然放大环境音

2. 视觉元素需包含：
   - 至少3次「视线引导」构图（箭头/目光方向）
   - 关键道具的「三现法则」（第5/15/25秒出现）

3. 音频节奏遵循「321原则」：
   - 3次语音情绪波峰（愤怒/惊讶/感动）
   - 2次背景音乐骤停
   - 1次环境音突出（如摔门声）

4. 使用抖音2023Q3热门参数：
   - 字幕描边：2px #FF0000
   - 转场速度：0.3秒±0.05
   - 动态模糊：帧率补间到60fps