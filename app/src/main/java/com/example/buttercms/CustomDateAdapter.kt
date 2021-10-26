package com.example.buttercms

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import com.squareup.moshi.*
import java.text.ParseException
import java.util.*

class CustomDateAdapter : JsonAdapter<Date>() {

    private val formatWithFracSecTimeZone =
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.US)
    private val formatWithoutFracSec =
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
    private val simpleFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SimpleDateFormat")
    @FromJson
    override fun fromJson(reader: JsonReader): Date? {
        if (reader.peek() == JsonReader.Token.NULL) {
            return reader.nextNull()
        }
        return deserializeToDate(reader.nextString())
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: Date?) {
        if (value != null) {
            writer.value(value.toString())
        }
    }

    @Synchronized
    fun deserializeToDate(json: String): Date? {
        try {
            return formatWithFracSecTimeZone.parse(json)
        } catch (ignored: ParseException) {
        }
        try {
            return formatWithoutFracSec.parse(json)
        } catch (ignored: ParseException) {
        }
        return try {
            simpleFormat.parse(json)
        } catch (e: ParseException) {
            throw Exception(json, e)
        }
    }
}
