package com.vicksam.ferapp.functions

import android.graphics.Bitmap
import android.graphics.BitmapFactory

fun byteArrayToBitmap(byteArray: ByteArray?): Bitmap?{
    val bitmap = byteArray?.let {
        BitmapFactory.decodeByteArray(
            byteArray,
            0,
            it.size
        )
    }
    return bitmap
}