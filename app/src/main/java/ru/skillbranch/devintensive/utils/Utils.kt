package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?):Pair<String?,String?>{
        //TODO FIX ME!!! DONE


        var parts: List<String>? = fullName?.split(" " )
        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)
        if (fullName=="" || fullName==" "){
            firstName = null
            lastName = null
        }
        return firstName to lastName //return Pair(firstName,lastName)
    }

    fun transliteration(payload: String, devider:String = " "): String {

        // var latinSimbol = ""
        var stroka = ""

        for (i in payload){
            var latinSimbol =
                when(i.toLowerCase()){
                    'а'-> "a"
                    'б'-> "b"
                    'в'-> "v"
                    'г'-> "g"
                    'д'-> "d"
                    'е'-> "e"
                    'ё'-> "e"
                    'ж'-> "zh"
                    'з'-> "z"
                    'и'-> "i"
                    'й'-> "i"
                    'к'-> "k"
                    'л'-> "l"
                    'м'-> "m"
                    'н'-> "n"
                    'о'-> "o"
                    'п'-> "p"
                    'р'-> "r"
                    'с'-> "s"
                    'т'-> "t"
                    'у'-> "u"
                    'ф'-> "f"
                    'х'-> "h"
                    'ц'-> "c"
                    'ч'-> "ch"
                    'ш'-> "sh"
                    'щ'-> "sh'"
                    'ъ'-> ""
                    'ы'-> "i"
                    'ь'-> ""
                    'э'-> "e"
                    'ю'-> "yu"
                    'я'-> "ya"
                    ' '->devider
                    else -> i.toString()
                }
            if (Character.isUpperCase(i))
                stroka += latinSimbol.toUpperCase()
            else
                stroka +=latinSimbol
        }
        return stroka




    }

    fun toInitials(firstName:   String?, lastName: String?)= "${firstName?.get(0)}${lastName?.get(0)}"

}