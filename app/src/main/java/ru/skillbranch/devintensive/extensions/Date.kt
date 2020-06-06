package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.lang.Math.ceil
import java.lang.Math.floor
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.ceil
import kotlin.math.floor

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24* HOUR

fun Date.format(pattern:String ="HH:mm:ss dd.MM.yy"):String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)

}

fun Date.add(value:Int, units: TimeUnits = TimeUnits.SECOND):Date{
    var time = this.time

    time +=when(units){
        TimeUnits.SECOND->value * SECOND
        TimeUnits.MINUTE->value* MINUTE
        TimeUnits.HOUR->value* HOUR
        TimeUnits.DAY->value* DAY
    }
    this.time = time

    return this
}


fun Date.humanizeDiff(date:Date = Date()):String {
//    TODO("return ex one day or fiew minutes")
    var d = date.time - this.time
    val s = if ((d / DAY) > 0) "${(d / DAY)} ${if ((d/DAY) == 1L) "день" else if (((d/DAY)>1) && ((d/DAY)<5)) "дня" else "дней"}"
        else if ((d / HOUR)> 0) "${(d/HOUR) } ${if ((d/HOUR) == 1L) "час" else if (((d/HOUR)>1) && ((d/HOUR)<5)) "часа" else "часов"}"
        else if ((d / MINUTE)> 0) "${(d/MINUTE)} ${if ((d/MINUTE) == 1L) "минуту" else if (((d/MINUTE)>1) && ((d/MINUTE)<5)) "минуты" else "минут"}"
        else if ((d / SECOND)> 0) "${(d/SECOND)} ${if ((d/SECOND) == 1L) "секунду" else if (((d/SECOND)>1) && ((d/SECOND)<5)) "секунды" else "секунд"}"
        else ""

    var t=
    when (d){
        in 0..1000 ->  "только что"
        in 1000..45000 ->  "несколько секунд назад"
        in 45_000..75_000 ->  "минуту назад"
        in 75_000..120_000 ->  "2 минуты назад"
        in 120_000..300_000 ->  "${d/ MINUTE} минуты назад"
        in 300_000..2_700_000 ->  "${d / MINUTE} минут назад"
        

        else->""
    }

    return t
}
enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}