package org.example

import com.google.gson.annotations.SerializedName

data class GitHubRepository(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("private") val isPrivate: Boolean,
)
