package com.koombea.presenter.ui.pin.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.koombea.presenter.R
import com.koombea.presenter.ui.theme.PURPLE
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun PinScreen(title: String, onValidatedPin: () -> Unit) {
    var count by remember {
        mutableStateOf(0)
    }

    Column(
        Modifier
            .fillMaxWidth()
            .background(PURPLE)
    ) {
        Text(
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(top = 90.dp),
            text = title,
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.W600,
            textAlign = TextAlign.Center
        )
        PinIndicator(
            modifier = Modifier.align(CenterHorizontally), count
        )
        Spacer(modifier = Modifier.weight(1f))
        NumericKeyboard(
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(bottom = 32.dp), count, {
                if (count < 4) {
                    count += 1
                }
            }, onValidatedPin
        )
    }
}

@Composable
fun NumericKeyboard(
    modifier: Modifier,
    count: Int,
    onNext: () -> Unit,
    onValidatedPin: () -> Unit
) {
    Column(modifier) {
        Row {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onNext() },
                textAlign = TextAlign.Center,
                text = "1",
                fontSize = 48.sp,
                color = Color.White,
                fontWeight = FontWeight.W500,
            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onNext() },
                textAlign = TextAlign.Center,
                text = "2",
                fontSize = 48.sp,
                color = Color.White,
                fontWeight = FontWeight.W500,
            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onNext() },
                textAlign = TextAlign.Center,
                text = "3",
                fontSize = 48.sp,
                color = Color.White,
                fontWeight = FontWeight.W500,
            )
        }
        Row {
            Text(
                text = "4",
                fontSize = 48.sp,
                textAlign = TextAlign.Center,
                color = Color.White,
                fontWeight = FontWeight.W500,
                modifier = Modifier
                    .weight(1f)
                    .clickable { onNext() },
            )
            Text(
                text = "5",
                fontSize = 48.sp,
                textAlign = TextAlign.Center,
                color = Color.White,
                fontWeight = FontWeight.W500,
                modifier = Modifier
                    .weight(1f)
                    .clickable { onNext() },
            )
            Text(
                text = "6",
                fontSize = 48.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.W500,
                modifier = Modifier
                    .weight(1f)
                    .clickable { onNext() },
            )
        }
        Row {
            Text(
                text = "7",
                fontSize = 48.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.W500,
                modifier = Modifier
                    .weight(1f)
                    .clickable { onNext() },
            )
            Text(
                text = "8",
                fontSize = 48.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.W500,
                modifier = Modifier
                    .weight(1f)
                    .clickable { onNext() },
            )
            Text(
                text = "9",
                fontSize = 48.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.W500,
                modifier = Modifier
                    .weight(1f)
                    .clickable { onNext() },
            )
        }
        Row {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "0", fontSize = 48.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.W500,
                modifier = Modifier
                    .weight(1f)
                    .clickable { onNext() },
            )
            Image(
                painter = painterResource(id = R.drawable.next),
                modifier = Modifier
                    .size(56.dp)
                    .weight(1f)
                    .align(CenterVertically)
                    .clickable { if (count >= 4) onValidatedPin() },
                contentDescription = "next"
            )
        }
    }
}

@Composable
fun PinIndicator(
    modifier: Modifier,
    countKey: Int,
) {
    Row(
        modifier = modifier.padding(top = 92.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        repeat(countKey) {
            val color = Color.White
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(size = 24.dp)
                    .background(color)
            )
        }
        repeat(4 - countKey) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(size = 24.dp)
                    .background(PURPLE)
                    .border(BorderStroke(2.dp, Color.White), CircleShape)
            )
        }
    }
}