package com.example.topten.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.topten.R

@Composable
fun CustomDialog(
    customTitle: String,
    customText: String,
    confirmText: String,
    dismissText: String,
    onConfirm: () -> Unit, onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        modifier = Modifier
            .border(
                width = 2.dp,
                color = White,
                shape = RoundedCornerShape(8.dp)
            ),
        shape = RoundedCornerShape(8.dp),
        title = { Text(customTitle) },
        text = { Text(customText) },
        containerColor = MaterialTheme.colorScheme.surface,
        titleContentColor = White,
        textContentColor = White,
        confirmButton = {

            Button(
                onClick = onConfirm,
                modifier = Modifier
                    .fillMaxWidth(0.4f),
                border = BorderStroke(2.dp, White),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.background),
                    contentColor = White
                ),
                contentPadding = PaddingValues(0.dp)

            ) {
                Text(confirmText)
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                modifier = Modifier
                    .fillMaxWidth(0.4f),
                border = BorderStroke(2.dp, White),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.background),
                    contentColor = White
                ),
                contentPadding = PaddingValues(0.dp)

            ) {
                Text(dismissText)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDialog(
    title: String,
    message: String,
    onConfirm: () -> Unit
) {
    BasicAlertDialog(
        onDismissRequest = onConfirm,
        modifier = Modifier
            .border(
                width = 2.dp,
                color = White,
                shape = RoundedCornerShape(8.dp)
            ),
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            tonalElevation = AlertDialogDefaults.TonalElevation,
            color = colorResource(R.color.background),
            shape = RoundedCornerShape(12.dp)

        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Text(
                    text = title,
                    Modifier.fillMaxWidth(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = White
                )
                Text(
                    text = AnnotatedString.fromHtml(
                        message
                    ),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    color = White
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    DefaultButton(
                        onConfirm,
                        stringResource(R.string.ok),
                        Modifier.fillMaxWidth(0.5f)
                    )
                }
            }
        }
    }
}