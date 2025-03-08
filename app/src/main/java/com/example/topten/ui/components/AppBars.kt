package com.example.topten.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.topten.R
import com.example.topten.ui.theme.TopTenDark

@Composable
fun Header(startIconRes: Int, endIconRes: Int, headerText: String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Image(
            painter = painterResource(id = startIconRes),
            contentDescription = null,
            modifier = Modifier
                .size(width = 50.dp, height = 30.dp)
        )

        Text(
            text = headerText,
            color = colorResource(id = R.color.white),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp),
            letterSpacing = 1.5.sp
        )

        Image(
            painter = painterResource(id = endIconRes),
            contentDescription = null,
            modifier = Modifier
                .size(width = 50.dp, height = 30.dp)
        )
    }
}

@Composable
fun FooterImage() {
    Image(
        painter = painterResource(id = R.drawable.footer),
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
    )
}

@Composable
fun SectionDivider(includeSpacerBefore: Boolean = true, includeSpacerAfter: Boolean = true, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (includeSpacerBefore) {
            Spacer(modifier = Modifier.weight(1f))
        }

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .padding(vertical = 10.dp),
            color = TopTenDark
        )

        if (includeSpacerAfter) {
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}