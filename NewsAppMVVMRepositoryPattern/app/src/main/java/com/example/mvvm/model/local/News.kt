package com.example.mvvm.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class News(
    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String,

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: String,

    @ColumnInfo(name = "image")
    @SerializedName("image")
    val image: String,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String,

    @ColumnInfo(name = "author")
    @SerializedName("author")
    val author: String,

    @ColumnInfo(name= "category")
    @SerializedName("category")
    val category: List<String>?
)
