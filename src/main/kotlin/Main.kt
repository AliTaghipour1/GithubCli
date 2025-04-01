package org.example

const val getUserInfoCommandPrefix = "getUser"
const val getLocalUsersListCommandPrefix = "getLocalUsersList"
const val getLocalUserCommandPrefix = "getLocalUser"
const val getLocalRepoCommandPrefix = "getLocalRepo"
const val exitCommandPrefix = "exit"

suspend fun main() {
    println("Welcome to my github cli - enter help to see command details")

    val handler: CommandHandler = CommandHandlerImpl()

    while (true) {
        print("> ")
        val input = readlnOrNull()
        val command = input?.trim()

        when {
            command.isNullOrBlank() -> continue
            command.startsWith(exitCommandPrefix) -> break
            command.startsWith(getUserInfoCommandPrefix) -> {
                val result = handler.HandleGetUser(command.removePrefix(getUserInfoCommandPrefix).trim())
                println("User info: $result")
            }

            command.startsWith(getLocalUserCommandPrefix) -> {
                val result = handler.HandleGetLocalUser(command.removePrefix(getLocalUserCommandPrefix).trim())
                println("Local User info: $result")
            }

            command.startsWith(getLocalRepoCommandPrefix) -> {
                val result = handler.HandleGetLocalRepository(command.removePrefix(getLocalRepoCommandPrefix).trim())
                println("Local User info: $result")
            }

            command.startsWith(getLocalUsersListCommandPrefix) -> {
                val result = handler.HandleGetAllLocalUsers()
                println("Users info: $result")
            }

            else -> println("You entered: $command")
        }
    }

    println("Goodbye!")
}