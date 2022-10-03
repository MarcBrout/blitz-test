package com.test.blitz.ui.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.test.blitz.ui.theme.Typography

@Composable
fun SimpleDialog (
    title: String = "Impossible Action",
    text: String = "An error occurred.\nPlease try again later.",
    showDismissButton: Boolean = true,
    dismissText: String = "Close",
    onDismiss: () -> Unit = {},
    confirmText: String = "Ok",
    onConfirm: () -> Unit = {},
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = title,
                style = Typography.subtitle1,
            )
        },
        text = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = text,
                style = Typography.body1
            )
        },
        buttons = {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.5.dp)
                        .background(color = Color.LightGray)
                )
                Row(
                    Modifier
                        .height(IntrinsicSize.Max)
                        .fillMaxWidth()
                ) {
                    if (showDismissButton) {
                        DialogButton(
                            title = dismissText,
                            onClick = onDismiss,
                        )
                        Box(
                            modifier = Modifier
                                .width(0.5.dp)
                                .fillMaxHeight()
                                .background(color = Color.LightGray)
                        )
                    }
                    DialogButton(
                        title = confirmText,
                        onClick = onConfirm,
                    )
                }
            }
        },
        shape = RoundedCornerShape(
            size = 20.dp
        )
    )
}

@Composable
fun RowScope.DialogButton(
    modifier: Modifier = Modifier,
    title: String,
    textColor: Color = Color.DarkGray,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier.weight(1f),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp),
    ) {
        Text(
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            text = title,
            style = Typography.button,
        )
    }
}