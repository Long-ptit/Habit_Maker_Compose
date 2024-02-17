package longtx.berlin.habittracker.ui.screens.onboard

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import longtx.berlin.habittracker.R
import longtx.berlin.habittracker.ui.theme.HabitTrackerTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardScreen(items: OnboardingItems) {
    Column(modifier = Modifier.fillMaxSize()) {
        val pageCount = 5
        val pagerState = rememberPagerState(pageCount = {
            5
        })
        val coroutineScope = rememberCoroutineScope()

        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        Text(
            text = stringResource(id = items.title),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 32.dp),
            fontSize = 32.sp
        )

        HorizontalPager(
            state = pagerState, modifier = Modifier
                .fillMaxHeight(0.6f)
                .fillMaxWidth()
                .background(Color.Cyan)
        ) { page ->
            Text(
                text = "Page: $page",
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = items.image),
                    contentDescription = null,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentScale = ContentScale.FillBounds
                )
            }
        }
        Text(
            text = stringResource(id = items.detail), modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp), textAlign = TextAlign.Center
        )
        BottomSection(pagerState)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomSection(pagerState: PagerState) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Skip")
        Indicators(4, pagerState.currentPage)
        Text(text = "Next")
    }
}

@Composable
fun RowScope.Indicators(pagerCount: Int, currentPageIteration: Int) {
    Row(
        modifier = Modifier
            .weight(1f),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerCount) { iteration ->
            val color = if (currentPageIteration == iteration) Color.DarkGray else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HabitTrackerTheme {
        OnboardScreen(OnboardingItems.ONE)
    }
}

val listData = listOf<OnboardingItems>(
    OnboardingItems.ONE,
    OnboardingItems.TWO,
    OnboardingItems.THREE,
    OnboardingItems.FOUR
)

enum class OnboardingItems(
    @StringRes val title: Int,
    @DrawableRes val image: Int,
    @StringRes val detail: Int = R.string.onboard_detail
) {
    ONE(R.string.onboard_1, R.drawable.onboard_1),
    TWO(R.string.onboard_2, R.drawable.onboard_2),
    THREE(R.string.onboard_3, R.drawable.onboard_3),
    FOUR(R.string.onboard_4, R.drawable.onboard_4)
}