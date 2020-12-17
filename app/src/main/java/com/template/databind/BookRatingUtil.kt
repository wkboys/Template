package com.template.databind

class BookRatingUtil {
    companion object{
        @JvmStatic
        fun getRatingString(rating:Int) : String{
            when (rating){
                0 -> {
                    return "零星"
                }
                1 -> {
                    return "一星"
                }
                2 -> {
                    return "二星"
                }
                3 -> {
                    return "三星"
                }
            }
            return ""
        }
    }
}