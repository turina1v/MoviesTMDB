package com.gabilheri.moviestmdb.data.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by [Marcus Gabilheri](mailto:marcus@gabilheri.com)
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 10/9/16.
 */
class Genre : Parcelable {
    var id = 0
        private set
    var name: String? = null
        private set

    constructor() {}

    fun setId(id: Int): Genre {
        this.id = id
        return this
    }

    fun setName(name: String?): Genre {
        this.name = name
        return this
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(name)
    }

    protected constructor(`in`: Parcel) {
        id = `in`.readInt()
        name = `in`.readString()
    }

    companion object {
        val CREATOR: Parcelable.Creator<Genre> = object : Parcelable.Creator<Genre> {
            override fun createFromParcel(source: Parcel): Genre? {
                return Genre(source)
            }

            override fun newArray(size: Int): Array<Genre?> {
                return arrayOfNulls(size)
            }
        }
    }
}