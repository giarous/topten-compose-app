package com.example.topten.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.topten.R

@Composable
fun DefaultButton(
    onClick: () -> Unit,
    buttonText: String,
    modifier: Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .border(
                width = 3.dp,
                color = White,
                shape = RoundedCornerShape(8.dp)
            ),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            colorResource(id = R.color.background),
                            colorResource(id = R.color.topten_dark)
                        ),
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Text(
                text = buttonText,
                style = TextStyle(
                    color = White,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun ColoredButton(
    index: Int,
    isEnabled: Boolean,
    modifier: Modifier,
    onClick: () -> Unit,
    backgroundColor: Color
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(40.dp)
            .border(
                width = 2.dp,
                color = White,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 0.dp),

        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.LightGray
        ),
        enabled = isEnabled
    ) {
        Text(
            text = index.toString(),
            style = TextStyle(
                color = White,
                fontWeight = FontWeight.Bold
            ),
        )
    }
}
