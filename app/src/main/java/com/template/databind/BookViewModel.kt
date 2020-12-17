package com.template.databind

import androidx.lifecycle.MutableLiveData
import com.template.module_common.base.BaseViewModel

class BookViewModel : BaseViewModel() {

     private var mbook: MutableLiveData<Book> = MutableLiveData<Book>()

    fun getBook() = mbook
    fun setBook(bookdata: Book) {
        mbook.value = bookdata
    }

    override fun onCleared() {
        super.onCleared()
    }

}