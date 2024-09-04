package com.example.starwars.model.uimodel

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class UIPlayer(
    val id: Int,
    val score: Int,
    val name: String,
    val result: MatchResult
) : Parcelable

enum class MatchResult {
    Win, Loss, Draw
}
