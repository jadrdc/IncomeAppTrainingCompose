package com.koombea.presenter.ui.onboarding.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.koombea.presenter.ui.onboarding.model.CarouselElement
import com.koombea.presenter.ui.theme.DEFAULT_BLACK
import com.koombea.presenter.ui.theme.LIGHT_GRAY
import com.koombea.presenter.ui.theme.PURPLE
import com.koombea.presenter.ui.theme.PURPLE_BUTTON
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(goToLogin: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        CarouselGallery(modifier = Modifier, CarouselElement.getCarouselList(), goToLogin)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DotIndicators(
    modifier: Modifier,
    pageCount: Int = 3,
    pagerState: PagerState
) {
    Row(
        modifier = modifier
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        repeat(pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration) PURPLE else LIGHT_GRAY
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(size = 16.dp)
                    .background(color)
            )
        }
    }
}

@Composable
fun CarouselItem(@DrawableRes pict: Int, title: String, subTitle: String, modifier: Modifier) {
    Column(
        Modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = pict),
            contentDescription = "background",
            modifier = modifier
                .fillMaxWidth()
                .align(CenterHorizontally)
                .size(312.dp)
        )
        Text(
            text = title,
            fontSize = 32.sp,
            lineHeight = 39.sp,
            fontWeight = FontWeight.W700,
            textAlign = TextAlign.Center,
            color = DEFAULT_BLACK, modifier = modifier
                .fillMaxWidth()
                .align(CenterHorizontally)

        )
        Text(
            text = subTitle,
            fontSize = 16.sp,
            fontWeight = FontWeight.W500,
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
                .align(CenterHorizontally), color = LIGHT_GRAY,
            maxLines = 2
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarouselGallery(modifier: Modifier, list: List<CarouselElement>, goToLogin: () -> Unit) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        val pagerState: PagerState = rememberPagerState()
        HorizontalPager(pageCount = 3, state = pagerState) { page ->
            val item = list[page]
            CarouselItem(
                item.pict, item.title, item.subtitle,
                modifier
            )
        }
        DotIndicators(
            modifier = modifier.align(CenterHorizontally),
            pageCount = 3,
            pagerState = pagerState,
        )
        NavigationButtons(pagerState) {
            goToLogin()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NavigationButtons(pagerState: PagerState, goToLogin: () -> Unit) {
    val isNotLoginStep = pagerState.currentPage < 2
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        if (isNotLoginStep) {
            Button(
                shape = RoundedCornerShape(16.dp),
                onClick = {
                    scope.launch {
                        pagerState.scrollToPage(pagerState.currentPage + 1)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 33.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PURPLE)
            ) {
                Text(text = "Next", fontSize = 18.sp)
            }
            Button(
                shape = RoundedCornerShape(16.dp),
                onClick = { goToLogin() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PURPLE_BUTTON)
            ) {
                Text(text = "Skip", fontSize = 18.sp, color = PURPLE)
            }
        } else {
            Button(
                shape = RoundedCornerShape(16.dp),
                onClick = {
                    goToLogin()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 33.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PURPLE)
            ) {
                Text(text = "Login", fontSize = 18.sp)
            }
        }
    }
}