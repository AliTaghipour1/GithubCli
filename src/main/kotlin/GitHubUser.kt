package org.example

import com.google.gson.annotations.SerializedName

data class GitHubUser(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("bio") val bio: String,
    @SerializedName("followers") val followers: Int,
    @SerializedName("following") val following: Int,
    @SerializedName("repos_url") val reposUrl: String,
    var publicRepositories: List<GitHubRepository>,
)
