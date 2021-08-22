package ru.geekbrains.appmvp.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRepo(
    @Expose val id: String? = null,
    @Expose val fullName: String? = null,
    @Expose val description: String? = null,
    @Expose val forksCount: Int? = null
) : Parcelable

