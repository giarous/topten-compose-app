package com.example.topten.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
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
import com.example.topten.data.Player
import com.example.topten.ui.theme.buttonColors

@Composable
fun TaskContainer(taskText: String, isFlipped: Boolean = false) {

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
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        )
        {
            ColoredButton(0, buttonState[0], Modifier.weight(1f), { onButtonClicked(0) })
            ColoredButton(1, buttonState[1], Modifier.weight(1f), { onButtonClicked(1) })
            ColoredButton(2, buttonState[2], Modifier.weight(1f), { onButtonClicked(2) })
            ColoredButton(3, buttonState[3], Modifier.weight(1f), { onButtonClicked(3) })
            ColoredButton(4, buttonState[4], Modifier.weight(1f), { onButtonClicked(4) })
        }

        Row(
            Modifier.padding(vertical = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        )
        {
            ColoredButton(5, buttonState[5], Modifier.weight(1f), { onButtonClicked(5) })
            ColoredButton(6, buttonState[6], Modifier.weight(1f), { onButtonClicked(6) })
            ColoredButton(7, buttonState[7], Modifier.weight(1f), { onButtonClicked(7) })
            ColoredButton(8, buttonState[8], Modifier.weight(1f), { onButtonClicked(8) })
            ColoredButton(9, buttonState[9], Modifier.weight(1f), { onButtonClicked(9) })
        }
    }
}

@Composable
fun ButtonContainerMini(
    buttonState: List<Boolean>,
) {
    Row(
        Modifier
            .padding(vertical = 5.dp, horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        repeat(10) { index ->
            ColoredButton(
                index = index,
                isEnabled = buttonState[index],
                modifier = Modifier.weight(1f),
                { },
                true
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
                .height(IntrinsicSize.Min),
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

@Composable
fun PlayerCard(
    onInfoClick: () -> Unit,
    onSelectClick: () -> Unit,
    modifier: Modifier = Modifier,
    playerIndex: Int,
    playerList: List<Player>
) {
    val player = playerList.getOrNull(playerIndex)
    val borderColor =
        if (player?.isActive == true) buttonColors[10] else buttonColors[player?.level!! - 1]
    OutlinedCard(
        modifier = modifier.aspectRatio(1.5f),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.outlinedCardColors(),
        elevation = CardDefaults.cardElevation(),
        border = BorderStroke(2.dp, borderColor),
    ) {

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 6.dp, vertical = 0.dp)
                .align(Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = player.name,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(bottom = 2.dp),
                textAlign = TextAlign.Center,
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold
            )


            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                PlayerCardButton(onClick = onInfoClick, "Info", Modifier.weight(1f))
                Spacer(Modifier.width(8.dp))
                PlayerCardButton(
                    onClick = onSelectClick,
                    if (player.isActive) "Select" else player.level.toString(),
                    Modifier.weight(1f),
                    player.isActive
                )
            }
        }
    }
}

@Composable
fun PlayerCardContainer(
    playerList: List<Player>,
    onInfoClicked: (Int) -> Unit,
    onSelectClick: (Int) -> Unit
) {
    Column(Modifier.padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            Modifier
                .padding(vertical = 5.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            PlayerCard(
                { onInfoClicked(0) },
                { onSelectClick(0) },
                Modifier.weight(1f),
                0,
                playerList
            )
            PlayerCard(
                { onInfoClicked(1) },
                { onSelectClick(1) },
                Modifier.weight(1f),
                1,
                playerList
            )
            PlayerCard(
                { onInfoClicked(2) },
                { onSelectClick(2) },
                Modifier.weight(1f),
                2,
                playerList
            )
            PlayerCard(
                { onInfoClicked(3) },
                { onSelectClick(3) },
                Modifier.weight(1f),
                3,
                playerList
            )

        }
        Row(
            Modifier.padding(vertical = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {

            PlayerCard(
                { onInfoClicked(4) },
                { onSelectClick(4) },
                Modifier.weight(1f),
                4,
                playerList
            )
            PlayerCard(
                { onInfoClicked(5) },
                { onSelectClick(5) },
                Modifier.weight(1f),
                5,
                playerList
            )
            PlayerCard(
                { onInfoClicked(6) },
                { onSelectClick(6) },
                Modifier.weight(1f),
                6,
                playerList
            )
            PlayerCard(
                { onInfoClicked(7) },
                { onSelectClick(7) },
                Modifier.weight(1f),
                7,
                playerList
            )
        }
        Row(
            modifier = Modifier
                .padding(vertical = 5.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.Bottom
        ) {

            Image(
                painter = painterResource(id = R.drawable.topten_scale_logo),
                contentDescription = null,
                modifier = Modifier
                    .padding(5.dp)
                    .weight(2f)
                    .height(50.dp),
                contentScale = ContentScale.Fit,
                alignment = Alignment.TopCenter
            )
            Spacer(modifier = Modifier.width(0.dp))
            PlayerCard(
                { onInfoClicked(8) },
                { onSelectClick(8) },
                Modifier.weight(1f),
                8,
                playerList
            )
            PlayerCard(
                { onInfoClicked(9) },
                { onSelectClick(9) },
                Modifier.weight(1f),
                9,
                playerList
            )
        }
    }
}