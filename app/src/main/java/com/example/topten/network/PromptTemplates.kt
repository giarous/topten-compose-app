package com.example.topten.network

object PromptTemplates {
    private const val ALL_PLAYERS_PROMPT = """
        You will be given a task that contains an implicit or explicit scale from low to high (e.g., from worst to best, from least effective to most effective, from boring to exciting, etc.).

        Generate 10 distinct responses to this task, each corresponding to a level on a scale from 1 (lowest) to 10 (highest).  
        The responses should gradually progress along the scale, with:
        - Response 1 being the weakest, least impressive, or most undesirable.
        - Response 10 being the strongest, most impressive, or most desirable.

        Your answer must be a valid JSON object with keys as numbers from 1 to 10 and values as each player's response. For example:
        
        {
          "1": "Player 1's answer...",
          "2": "Player 2's answer...",
          ...
          "10": "Player 10's answer..."
        }
        
        Make sure the output is only this JSON.

        Task: %s
    """

    const val NEW_TASK_OPENAI_PROMPT =
        """You are a creative game master. Generate a task in ALL UPPERCASE that challenges players to describe or choose something along a scale. Your output must include a vivid scenario and end with a scale instruction that uses HTML font tags to indicate the two ends. Use the following format exactly:

        [TASK DESCRIPTION] FROM<font color='#85CA18'> [DESCRIPTION FOR THE LOWER EXTREME] </font> TO <font color='red'> [DESCRIPTION FOR THE UPPER EXTREME] </font>

        For example:
        "DESCRIBE YOUR IMAGINARY INVENTION FROM <font color='#85CA18'>SOMETHING COMPLETELY USELESS</font> TO <font color='red'>«EVERYONE WANTS TO BUY IT»</font>."

        Make sure to maintain the same formation, spacing, and HTML formatting for the colored text. Let your creativity flow!
        """

    fun getFormattedPrompt(task: String): String {
        return ALL_PLAYERS_PROMPT.format(task)
    }
}