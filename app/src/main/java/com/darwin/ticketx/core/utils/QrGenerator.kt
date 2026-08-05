package com.darwin.ticketx.core.utils

import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import androidx.core.graphics.createBitmap
import androidx.core.graphics.set


object QrGenerator {

    fun generate(
        content: String,
        size: Int = 512
    ): Bitmap {

        val bitMatrix: BitMatrix =
            MultiFormatWriter()
                .encode(
                    content,
                    BarcodeFormat.QR_CODE,
                    size,
                    size
                )


        val bitmap = createBitmap(size, size, Bitmap.Config.RGB_565)


        for (x in 0 until size) {
            for (y in 0 until size) {

                bitmap[x, y] = if (bitMatrix[x, y])
                    Color.BLACK
                else
                    Color.WHITE
            }
        }


        return bitmap
    }
}