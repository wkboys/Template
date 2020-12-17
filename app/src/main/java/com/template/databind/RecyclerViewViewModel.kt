package com.template.databind

class RecyclerViewViewModel {
    fun getBooks(): MutableList<Book> {
        var books: MutableList<Book> =ArrayList<Book>()
        for (i in 0 until  10){
            var Book=Book("wkboys+${i}","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1608186946041&di=576875cd4289a49c1a6d22602db3a321&imgtype=0&src=http%3A%2F%2Fbpic.588ku.com%2Felement_origin_min_pic%2F01%2F12%2F51%2F8956f6057d53239.jpg",i)
            books.add(Book)
        }
        return books
    }
}