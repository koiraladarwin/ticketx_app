package com.darwin.ticketx.feature_ticket.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.darwin.ticketx.feature_ticket.domain.model.EventUi
import kotlin.math.absoluteValue

@SuppressLint("FrequentlyChangingValue")
@Composable
fun FeaturedCarousel(
    events: List<EventUi>,
    onClick: (EventUi) -> Unit
) {

    val pagerState = rememberPagerState {
        events.size
    }

    Column {


        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 0.dp),
            pageSpacing = 12.dp
        ) { page ->

            val pageOffset = (
                    (pagerState.currentPage - page) +
                            pagerState.currentPageOffsetFraction
                    ).absoluteValue

            FeaturedEventCard(
                event = events[page],
                onClick = {
                    onClick(events[page])
                },
                modifier = Modifier.graphicsLayer {

                    val scale = lerp(
                        start = 0.92f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )

                    scaleX = scale
                    scaleY = scale

                    alpha = lerp(
                        start = 0.65f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )

                }
            )

        }

        Spacer(Modifier.height(14.dp))

        PageIndicator(
            pageCount = events.size,
            currentPage = pagerState.currentPage
        )

    }

}