package com.example.starwars.model.uimodel

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.Parceler

@Keep
@Parcelize
data class UIMatchListItem(
    val player1: UIPlayer,
    val player2: UIPlayer
) : Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    companion object : Parceler<UIMatchListItem> {
        override fun UIMatchListItem.write(p0: Parcel, p1: Int) {
            TODO("Not yet implemented")
        }

        override fun create(parcel: Parcel): UIMatchListItem = TODO()
    }
}