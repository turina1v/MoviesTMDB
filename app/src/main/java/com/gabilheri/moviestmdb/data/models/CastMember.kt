package com.gabilheri.moviestmdb.data.models

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

/**
 * Created by [Marcus Gabilheri](mailto:marcus@gabilheri.com)
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 10/9/16.
 */
class CastMember : Parcelable {
    var id = 0
        private set
    var character: String? = null
        private set
    var name: String? = null
        private set
    var order = 0
        private set

    @Json(name = "cast_id")
    var castId = 0
        private set

    @Json(name = "credit_id")
    var creditId: String? = null
        private set

    @Json(name = "profile_path")
    var profilePath: String? = null
        private set

    constructor() {}

    fun setId(id: Int): CastMember {
        this.id = id
        return this
    }

    fun setCastId(castId: Int): CastMember {
        this.castId = castId
        return this
    }

    fun setCharacter(character: String?): CastMember {
        this.character = character
        return this
    }

    fun setCreditId(creditId: String?): CastMember {
        this.creditId = creditId
        return this
    }

    fun setName(name: String?): CastMember {
        this.name = name
        return this
    }

    fun setOrder(order: Int): CastMember {
        this.order = order
        return this
    }

    fun setProfilePath(profilePath: String?): CastMember {
        this.profilePath = profilePath
        return this
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(character)
        dest.writeString(name)
        dest.writeInt(order)
        dest.writeInt(castId)
        dest.writeString(creditId)
        dest.writeString(profilePath)
    }

    protected constructor(`in`: Parcel) {
        id = `in`.readInt()
        character = `in`.readString()
        name = `in`.readString()
        order = `in`.readInt()
        castId = `in`.readInt()
        creditId = `in`.readString()
        profilePath = `in`.readString()
    }

    companion object {
        val CREATOR: Parcelable.Creator<CastMember> = object : Parcelable.Creator<CastMember> {
            override fun createFromParcel(source: Parcel): CastMember? {
                return CastMember(source)
            }

            override fun newArray(size: Int): Array<CastMember?> {
                return arrayOfNulls(size)
            }
        }
    }
}