package com.gabilheri.moviestmdb.data.models

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

/**
 * Created by [Marcus Gabilheri](mailto:marcus@gabilheri.com)
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 10/10/16.
 */
class CrewMember : Parcelable {
    var id = 0
        private set
    var job: String? = null
        private set
    var name: String? = null
        private set
    var department: String? = null
        private set

    @Json(name = "profile_path")
    var profilePath: String? = null
        private set

    constructor() {}

    fun setId(id: Int): CrewMember {
        this.id = id
        return this
    }

    fun setJob(job: String?): CrewMember {
        this.job = job
        return this
    }

    fun setName(name: String?): CrewMember {
        this.name = name
        return this
    }

    fun setDepartment(department: String?): CrewMember {
        this.department = department
        return this
    }

    fun setProfilePath(profilePath: String?): CrewMember {
        this.profilePath = profilePath
        return this
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(job)
        dest.writeString(name)
        dest.writeString(department)
        dest.writeString(profilePath)
    }

    protected constructor(`in`: Parcel) {
        id = `in`.readInt()
        job = `in`.readString()
        name = `in`.readString()
        department = `in`.readString()
        profilePath = `in`.readString()
    }

    companion object {
        val CREATOR: Parcelable.Creator<CrewMember> = object : Parcelable.Creator<CrewMember> {
            override fun createFromParcel(source: Parcel): CrewMember? {
                return CrewMember(source)
            }

            override fun newArray(size: Int): Array<CrewMember?> {
                return arrayOfNulls(size)
            }
        }
    }
}