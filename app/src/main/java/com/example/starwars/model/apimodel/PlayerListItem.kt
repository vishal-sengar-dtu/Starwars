package com.example.starwars.model.apimodel

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.Parceler

@Keep
@Parcelize
data class PlayerListItem(
    val icon: String,
    val id: Int,
    val name: String,
) : Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    companion object : Parceler<PlayerListItem> {
        override fun PlayerListItem.write(p0: Parcel, p1: Int) {
            TODO("Not yet implemented")
        }

        override fun create(parcel: Parcel): PlayerListItem = TODO()
    }
}

