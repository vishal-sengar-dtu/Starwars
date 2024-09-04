package com.example.starwars.model.uimodel

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class UIPlayerListItem(
    val id : Int,
    val icon: String,
    val name: String,
    val score: Int
) : Parcelable
