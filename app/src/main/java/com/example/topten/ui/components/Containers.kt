package com.example.topten.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.topten.R
import com.example.topten.ui.theme.BtnColor1
import com.example.topten.ui.theme.BtnColor10
import com.example.topten.ui.theme.BtnColor2
import com.example.topten.ui.theme.BtnColor3
import com.example.topten.ui.theme.BtnColor4
import com.example.topten.ui.theme.BtnColor5
import com.example.topten.ui.theme.BtnColor6
import com.example.topten.ui.theme.BtnColor7
import com.example.topten.ui.theme.BtnColor8
import com.example.topten.ui.theme.BtnColor9

@Composable
fun TaskContainer (taskText: String, isFlipped: Boolean = false) {

    val textSize = remember(taskText) {
        when {
            taskText.length < 150 -> 14.sp
            taskText.length < 230 -> 12.sp
            else -> 10.sp
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 5.dp
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.task_area),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { scaleX = if (isFlipped) -1f else 1f },
            contentScale = ContentScale.Fit,
        )

        Text(
            text = AnnotatedString.fromHtml(
                taskText
            ),
            color = colorResource(id = R.color.black),
            fontSize = textSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 40.dp)
                .padding(top = 20.dp)
        )
    }
}

@Composable
fun ButtonContainer(
    buttonState: List<Boolean>,
    onButtonClicked: (Int) -> Unit
) {
    Column(Modifier.padding(5.dp)) {
        Row(
            Modifier.padding(vertical = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp))
        {
            ColoredButton(
                1,
                buttonState[0],
                Modifier
                    .weight(1f), { onButtonClicked(0) }, BtnColor1
            )
            ColoredButton(2, buttonState[1],
                Modifier
                    .weight(1f), { onButtonClicked(1) }, BtnColor2
            )
            ColoredButton(3, buttonState[2],
                Modifier
                    .weight(1f), { onButtonClicked(2) }, BtnColor3
            )
            ColoredButton(4, buttonState[3],
                Modifier
                    .weight(1f), { onButtonClicked(3) }, BtnColor4
            )
            ColoredButton(5, buttonState[4],
                Modifier
                    .weight(1f), { onButtonClicked(4) }, BtnColor5
            )
        }

        Row(
            Modifier.padding(vertical = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp))
        {
            ColoredButton(6, buttonState[5],
                Modifier
                    .weight(1f), { onButtonClicked(5) }, BtnColor6
            )
            ColoredButton(7, buttonState[6],
                Modifier
                    .weight(1f), { onButtonClicked(6) }, BtnColor7
            )
            ColoredButton(8, buttonState[7],
                Modifier
                    .weight(1f), { onButtonClicked(7) }, BtnColor8
            )
            ColoredButton(9, buttonState[8],
                Modifier
                    .weight(1f), { onButtonClicked(8) }, BtnColor9
            )
            ColoredButton(10, buttonState[9],
                Modifier
                    .weight(1f), { onButtonClicked(9) }, BtnColor10
            )
        }
    }
}

@Composable
fun ScoreBoardContainer(numberOfUnicorns: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(horizontal = 10.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.game_area_image),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
            //.padding(5.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .align(Alignment.TopCenter)
                .height(IntrinsicSize.Min), // To make sure the row adjusts based on the tallest child
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = numberOfUnicorns.toString(),
                color = Color.White,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f, fill = false)
                    .wrapContentWidth(Alignment.End)
            )

            Text(
                text = ":",
                color = Color.White,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(8.dp, 0.dp, 8.dp, 8.dp)
                    .align(Alignment.CenterVertically)
            )

            Text(
                text = (8 - numberOfUnicorns).toString(),
                color = Color.White,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f, fill = false)
                    .wrapContentWidth(Alignment.Start)
            )

        }
    }
}