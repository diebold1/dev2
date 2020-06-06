package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User(
    val id : String,
    var firstName : String?,
    var lastName : String?,
    var avatar : String?,
    var rating : Int = 0,
    var respect : Int = 0,
    var lastVisit : Date?= null,
    var isOnline : Boolean = false


) {
    var introBit:String
    constructor(id:String,firstName: String?, lastName: String?):this(
        id =id,
        firstName=firstName,
        lastName=lastName,
        avatar=null)

    constructor(id:String) : this(id, "John", "Doe")

    init {
        introBit = gerIntro()
        println("It's alive!!! \n"+
                "${if(lastName==="Doe") "His name is $firstName $lastName" else "And his name $firstName $lastName"}\n")
    }

    companion object Factory {
        private  var lastId: Int = -1
        fun  makeUser(fullName:String) :User{
            lastId++
          val( firstName,lastName) = Utils.parseFullName(fullName)
           // if ((fullName != null)|| parts?.size!! > 1 )
                return  User(id= "$lastId",firstName = firstName,lastName = lastName)
          //  else
           //     return
        }
    }

    private fun gerIntro() = """
         wedwedew dwe de  
         dwed dwed dewd 
                  
         wedwedew dwe de 
          dwed dwed dewd 
          w
          edwedew dwe de 
         dwed dwed dewd 
         
         ${"\n\n\n"}
         $firstName $lastName
                           
    """.trimIndent()

    fun printMe()=
        println("""
            id: $id
            firstName: $firstName
            lastName: $lastName
            avatar: $avatar
            rating: $rating
            respect: $respect
            lastVisit: $lastVisit
            isOnline: $isOnline
        """.trimIndent())


}