package com.example.starwars.model.apimodel

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.Parceler

@Keep
@Parcelize
data class MatchListItem(
    val match: Int,
    val player1: Player,
    val player2: Player
) : Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    companion object : Parceler<MatchListItem> {
        override fun MatchListItem.write(p0: Parcel, p1: Int) {
            TODO("Not yet implemented")
        }

        override fun create(parcel: Parcel): MatchListItem = TODO()
    }
}