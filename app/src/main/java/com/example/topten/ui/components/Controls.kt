package com.example.topten.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.topten.R
import com.example.topten.ui.theme.TopTenTheme

@Composable
fun CustomSwitch(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit){

    Switch(
        checked = isChecked,
        onCheckedChange = { newCheckedState -> onCheckedChange(newCheckedState) },
        thumbContent = if (isChecked) {
            {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    modifier = Modifier.size(SwitchDefaults.IconSize),
                )
            }
        } else {
            null
        },

        colors = SwitchDefaults.colors(
            checkedThumbColor = colorResource(R.color.teal_200),
            checkedTrackColor = Color.Transparent,
            checkedBorderColor = colorResource(R.color.teal_200),
            uncheckedBorderColor = colorResource(R.color.teal_700),
            uncheckedThumbColor = colorResource(R.color.topten_dark),
            uncheckedTrackColor = Color.Transparent,
        ),
        modifier = Modifier
            .padding(5.dp)

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSlider(sliderPosition: Float, onSliderPositionChange: (Float) -> Unit){

    Slider(
        value = sliderPosition,
        onValueChange = {onSliderPositionChange(it)},
        valueRange = 1f..10f,
        steps = 9,
        modifier = Modifier
            .width(180.dp)
            .padding(0.dp),

        thumb = {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .background(color = colorResource(R.color.teal_200), shape = CircleShape)

            )
        },
        track = { sliderState ->

            SliderDefaults.Track(
                sliderState = sliderState,
                modifier = Modifier.scale(scaleX = 1f, scaleY = 0.3f),
                thumbTrackGapSize = 0.dp,
                drawStopIndicator = null,
                drawTick = { _, _ -> },
                colors = SliderDefaults.colors(
                    thumbColor = Color.Black,
                    activeTrackColor = colorResource(R.color.teal_200),
                    inactiveTrackColor = colorResource(R.color.topten_dark),
                    inactiveTickColor = Color.Transparent,
                    activeTickColor = Color.Transparent
                )
            )
        }
    )

}

@Preview
@Composable
fun CustomPreview(){
    TopTenTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.8f)
                .padding(24.dp)
        ){
            TaskDialog(title = "This is the title:",
                message = "DESCRIBE YOUR IMAGINARY INVENTION FROM <font color='#85CA18'> SOMETHING COMPLETELY USELESS</font> TO <font color='red'>«EVERYONE WANTS TO BUY IT».</font>",
                onConfirm = {  },
            )
        }
    }
}

