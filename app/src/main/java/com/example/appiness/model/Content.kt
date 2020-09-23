package com.example.appiness.model

import androidx.room.Entity
import androidx.room.PrimaryKey

sealed class Content {

    abstract fun getContentType(): ContentType
}

@Entity
data class DataModel(@PrimaryKey var login: String, var id: String) {

}

class DataModelEncapsulator : Content() {
    var data: List<ItemsItem>? = null

    override fun getContentType(): ContentType {
        return ContentType.DATAMODEL
    }

}
class ErrorModel : Content() {
    override fun getContentType(): ContentType {
        return ContentType.ERRORMODEL
    }

}


enum class ContentType {

    ERRORMODEL, DATAMODEL

}