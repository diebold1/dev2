package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.lang.Math.*
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
/*
1	 секунда	минута	час 	день
2-4	 секунды	минуты	часа	дня
5-20 секунд	    минут	часов	дней

0c    - 1с      "только что"
1с    - 45с     "несколько секунд назад"
45с   - 75с     "минуту назад"
75с   - 45мин   "N минут назад"
45мин - 75мин   "час назад"
75мин - 22ч     "N часов назад"
22ч   - 26ч     "день назад"
26ч   - 360д    "N дней назад"
>360д           "более года назад"
    */

//    TODO("return ex one day or fiew minutes")
   var d = date.time - this.time

   var t=
   when (abs(d)){
       in 0..1000 ->  "только что"
       in 1000..45_017 ->  "${if (d>0) "несколько секунд назад" else "через несколько секунд"}"
       in 45_000..75_000 -> "${if (d>0) "минуту назад" else "через минуту"}"
       in 76_000..2_700_019 -> when(d / MINUTE){
           1L->"${if(d>0) "${d/ MINUTE} минуту назад" else "через ${d/ MINUTE} минуту"}"
           in 2..4->"${if(d>0) "${d/ MINUTE} минуты назад" else "через ${d/ MINUTE} минуты"}"
           in 5..20->"${if(d>0) "${d/ MINUTE} минут назад" else "через ${d/ MINUTE} минут"}"
           in 21..45->when((d/ MINUTE) % 10){
               1L->"${if(d>0) "${d/ MINUTE} минуту назад" else "через ${d/ MINUTE} минуту"}"
               in 2..4->"${if(d>0) "${d/ MINUTE} минуты назад" else "через ${d/ MINUTE} минуты"}"
               0L , in 5..10->"${if(d>0) "${d/ MINUTE} минут назад" else "через ${d/ MINUTE} минут"}"
               else->"11"
           }
           else->"111"
       }
       in 2_700_000..4_500_000->"${if (d>0) "час назад" else "через час"}"
       in 4_500_000..79_200_019->when(d / HOUR){
           1L->"${if(d>0) "${d/ HOUR} час назад" else "через ${d/ HOUR} час"}"
           in 2..4->"${if(d>0) "${d/ HOUR} часа назад" else "через ${d/ HOUR} часа"}"
           in 5..20->"${if(d>0) "${d/ HOUR} часов назад" else "через ${d/ HOUR} часов"}"
           in 21..45->when((d/ HOUR) % 10){
               1L->"${if(d>0) "${d/ HOUR} час назад" else "через ${d/ HOUR} час"}"
               in 2..4->"${if(d>0) "${d/ HOUR} часа назад" else "через ${d/ HOUR} часа"}"
               0L , in 5..10->"${if(d>0) "${d/ HOUR} часов назад" else "через ${d/ HOUR} часов"}"
               else->"22"
           }
           else->"222"

       }
       in 79_200_000..93_600_033->"${if (d>0) "день назад" else "через день"}"
       in 93_600_000L..31_104_000_034L->when(d / DAY){
           1L->"${if(d>0) "${d/ DAY} день назад" else "через ${d/ DAY} день"}"
           in 2..4->"${if(d>0) "${d/ DAY} дня назад" else "через ${d/ DAY} дня"}"
           in 5..20->"${if(d>0) "${d/ DAY} дней назад" else "через ${d/ DAY} дней"}"
           in 21..360->when((d/ DAY) % 10){
               1L->"${if(d>0) "${d/ DAY} день назад" else "через ${d/ DAY} день"}"
               in 2..4->"${if(d>0) "${d/ DAY} дня назад" else "через ${d/ DAY} дня"}"
               0L , in 5..10->"${if(d>0) "${d/ DAY} дней назад" else "через ${d/ DAY} дней"}"
               else->"33"
           }
           else->"333"

       }
       else->"более года назад"
   }

   return t
}
enum class TimeUnits{
   SECOND,
   MINUTE,
   HOUR,
   DAY
}