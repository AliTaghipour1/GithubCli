package org.example

interface CommandHandler {
    suspend fun HandleGetUser(a: String): String
}


class CommandHandlerImpl : CommandHandler {
    override suspend fun HandleGetUser(username: String): String {

        if (username == "") {
            throw Exception("Empty username")
        }

        val userData =
            Dependencies.usersMap[username] //Todo: add methods to dependencies instead of passing map directly
        if (!userData.isNullOrBlank()) return userData

        val userInfo = Dependencies.gitHub.getUserInfo(username)
        Dependencies.usersMap[username] = userInfo.toString()
        return userInfo.toString()
    }


}
