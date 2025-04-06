package org.example

import retrofit2.HttpException

interface CommandHandler {
    suspend fun HandleGetUser(username: String): String
    suspend fun HandleGetLocalUser(username: String): String
    suspend fun HandleGetLocalRepository(repoName: String): String
    suspend fun HandleGetAllLocalUsers(): String
}


class CommandHandlerImpl : CommandHandler {
    override suspend fun HandleGetUser(username: String): String {

        if (username == "") {
            throw Exception("Empty username")
        }

        val userData =
            Dependencies.usersMap[username] //Todo: add methods to dependencies instead of passing map directly
        if (userData != null) {
            return userData.toString()
        }

        val userInfo: GitHubUser?
        try {
            userInfo = Dependencies.gitHub.getUserInfo(username)
        } catch (e: HttpException) {
            val error = Dependencies.gson.fromJson(
                e.response()?.errorBody()?.string(),
                ApiError::class.java
            )
            return error.toString()
        } catch (e: Exception) {
            return ""
        }


        val repositories =
            Dependencies.gitHub.getUserRepositories(username).filter { repo: GitHubRepository -> !repo.isPrivate }

        userInfo.publicRepositories = repositories
        Dependencies.usersMap[username] = userInfo
        for (repository in repositories) {
            Dependencies.publicRepositories[repository.name] = repository
        }
        return userInfo.toString()
    }

    override suspend fun HandleGetLocalUser(username: String): String {
        //Todo: add methods to dependencies instead of passing map directly
        val userData = Dependencies.usersMap[username] ?: return "not found"
        return userData.toString()

    }

    override suspend fun HandleGetLocalRepository(repoName: String): String {
        val result = Dependencies.publicRepositories[repoName] ?: return "not found"
        return result.toString()
    }

    override suspend fun HandleGetAllLocalUsers(): String {

        var result = ""
        Dependencies.usersMap.keys.forEach { key ->
            result += key + "\n"
        }

        return result
    }

}
