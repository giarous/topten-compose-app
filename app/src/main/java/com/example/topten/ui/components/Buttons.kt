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
import androidx.compose.material3.LocalTextStyle
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
import androidx.compose.ui.unit.sp
import com.example.topten.R
import com.example.topten.ui.theme.buttonColors

@Composable
fun DefaultButton(
    onClick: () -> Unit,
    buttonText: String,
    modifier: Modifier,
    isEnabled: Boolean = true
) {
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
        contentPadding = PaddingValues(0.dp),
        enabled = isEnabled
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
fun PlayerCardButton(
    onClick: () -> Unit,
    buttonText: String,
    modifier: Modifier,
    isEnabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(16.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        contentPadding = PaddingValues(0.dp),
        enabled = isEnabled
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .border(
                    width = 0.5.dp,
                    color = White,
                    shape = RoundedCornerShape(4.dp)
                )
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            colorResource(id = R.color.background),
                            colorResource(id = R.color.topten_dark)
                        ),
                    ),
                )
        ) {
            Text(
                text = buttonText,
                style = TextStyle(
                    color = White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 7.sp
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
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    isMini: Boolean = false
) {
    // Configuration based on size
    val height = if (isMini) 20.dp else 40.dp
    val borderWidth = if (isMini) 1.dp else 2.dp
    val cornerRadius = if (isMini) 4.dp else 8.dp
    val contentPadding =
        if (isMini) PaddingValues(horizontal = 4.dp, vertical = 2.dp) else PaddingValues()
    val text = (index + 1).toString()

    Button(
        onClick = onClick,
        modifier = modifier
            .height(height)
            .border(
                width = borderWidth,
                color = White,
                shape = RoundedCornerShape(cornerRadius)
            )
            .padding(horizontal = 0.dp),
        shape = RoundedCornerShape(cornerRadius),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColors[index],
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.LightGray
        ),
        contentPadding = contentPadding,
        enabled = isEnabled
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = White,
                fontWeight = FontWeight.Bold,
                fontSize = if (isMini) 10.sp else LocalTextStyle.current.fontSize
            )
        )
    }
}
