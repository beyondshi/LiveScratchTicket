package com.beyond.livescratchticket.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.PointerEventType.Companion.Move
import androidx.compose.ui.input.pointer.PointerEventType.Companion.Press
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.beyond.livescratchticket.R
import com.beyond.livescratchticket.TAG
import kotlin.random.Random

private val roles = arrayOf("老婆", "老公")

@Composable
fun addDetail(index: Int, arguments: String) {
    var linePath by remember { mutableStateOf(Offset.Zero) }
    val path by remember { mutableStateOf(Path()) }
    var random by remember { mutableStateOf(Random.nextInt(2)) }
    val result = roles[random]
    val res = when (index) {
        // 洗碗
        0 -> if (random == 0) {
            R.mipmap.dishwasher_woman
        } else {
            R.mipmap.dishwasher_man
        }
        // 拖地
        1 -> if (random == 0) {
            R.mipmap.mop_the_floor_woman
        } else {
            R.mipmap.mop_the_floor_man
        }

        else -> 0
    }


    val pathPaint = Paint().apply {
        alpha = 0f
        style = PaintingStyle.Stroke
        strokeWidth = 150f
        blendMode = BlendMode.SrcIn
        strokeJoin = StrokeJoin.Round
        strokeCap = StrokeCap.Round
    }

    val layerPaint = Paint().apply {
        color = Color.Gray
    }

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (title, content, refresh) = createRefs()
        Text(
            modifier = Modifier
                .constrainAs(title) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .padding(top = 20.dp),
            text = "刮一刮看谁$arguments",
            fontSize = 30.sp,
            color = Color.Black
        )
        Column(modifier = Modifier
            .constrainAs(content) {
                top.linkTo(title.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 40.dp)
            .pointerInput("dragging") {
                awaitEachGesture {
                    while (true) {
                        val event = awaitPointerEvent()
                        when (event.type) {
                            // 按住时，更新起始点
                            Press -> {
                                Log.i(TAG, "awaitEachGesture   Press")
                                path.moveTo(
                                    event.changes.first().position.x,
                                    event.changes.first().position.y
                                )
                            }
                            // 移动时，更新起始点 移动时，记录路径path
                            Move -> {
                                linePath = event.changes.first().position
                            }
                        }
                    }
                }
            }
            .drawWithContent {
                drawContent()
                drawIntoCanvas {
                    val rect = Rect(0f, 0f, size.width, size.height)
                    //从当前画布，裁切一个新的图层
                    it.saveLayer(rect, layerPaint)
                    it.drawRect(rect, layerPaint)

                    if (linePath.x > 0 && linePath.y > 0) {
                        path.lineTo(linePath.x, linePath.y)
                    }
                    it.drawPath(path, pathPaint)
                    it.restore()
                }
            }
        ) {
            Image(
                modifier = Modifier
                    .size(300.dp, 300.dp),
                painter = painterResource(id = res),
                contentDescription = "",
                contentScale = ContentScale.Fit
            )
            Text(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally),
                text = "$result",
                fontSize = 40.sp,
                color = Color.Red
            )
        }

        Button(
            modifier = Modifier
                .constrainAs(refresh) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(content.bottom)
                }
                .padding(top = 40.dp),
            onClick = {
                linePath = Offset.Zero
                random = Random.nextInt(2)
                path.reset()
            },
            content = {
                Text(
                    text = "再刮一次",
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }
        )
    }
}
