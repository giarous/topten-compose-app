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

    const val PLAYER_RESPONSES_PROMPT = """
        Simulate responses from 10 different players to the following task:
        %s
        Please output your answer as a valid JSON object with keys as numbers from 1 to 10 and values as each player's response. For example:
        {
          "1": "Player 1's answer...",
          "2": "Player 2's answer...",
          ...
          "10": "Player 10's answer..."
        }
        Make sure the output is only this JSON.
    """

    const val NEW_TASK_PROMPT = """Generate ONE imaginative game task with this structure:
        1. Start with a bold/capitalized scenario setup ending with an exclamation mark or colon
        2. Present two extremes using HTML color tags: <font color='#85CA18'>[basic version]</font> TO <font color='red'>[extreme version]</font>
        3. Keep scenarios playful and absurd, involving either:
    - Creative challenges ("Describe...", "Invent...", "Imagine...")
    - Dramatic reactions ("Show your reaction...", "Shout out...")
    - Unexpected twists ("...turned out to be...", "...wake up as...")
        4. Use exactly 2 color codes: #85CA18 for mundane/negative and red for exciting/positive
        5. Make it suitable for a party game about creativity and humor

Return ONLY the task without extra text, using HTML formatting for colors."""

    const val NEW_TASK_OPENAI_PROMPT = """You are a creative game master. Generate a task in ALL UPPERCASE that challenges players to describe or choose something along a scale. Your output must include a vivid scenario and end with a scale instruction that uses HTML font tags to indicate the two ends. Use the following format exactly:

[TASK DESCRIPTION] FROM<font color='#85CA18'> [DESCRIPTION FOR THE LOWER EXTREME] </font> TO <font color='red'> [DESCRIPTION FOR THE UPPER EXTREME] </font>

For example:
"DESCRIBE YOUR IMAGINARY INVENTION FROM <font color='#85CA18'>SOMETHING COMPLETELY USELESS</font> TO <font color='red'>«EVERYONE WANTS TO BUY IT»</font>."

Make sure to maintain the same formation, spacing, and HTML formatting for the colored text. Let your creativity flow!
"""

    const val NEW_TASK_OPENAI_CREATIVE = """You are a creative game master. Generate an imaginative task in ALL UPPERCASE that challenges players to engage in various actions—such as describing, choosing, acting, or improvising—based on a scale. Your task should include a vivid scenario and end with a scale instruction that uses HTML font tags to highlight the two extremes. Use the following exact format:

[TASK DESCRIPTION] FROM<font color='#85CA18'> [DESCRIPTION FOR THE LOWER EXTREME] </font> TO <font color='red'> [DESCRIPTION FOR THE UPPER EXTREME] </font>

For example:
"DESCRIBE YOUR IMAGINARY INVENTION FROM <font color='#85CA18'>SOMETHING COMPLETELY USELESS</font> TO <font color='red'>«EVERYONE WANTS TO BUY IT»</font>."

Let your creativity flow and ensure that the formation, spacing, and HTML formatting for the colored text remain exactly as shown.
"""
    fun getFormattedPrompt(task: String): String {
        return ALL_PLAYERS_PROMPT.format(task)
    }
}