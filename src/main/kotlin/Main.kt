package org.example

const val getUserInfoCommandPrefix = "getUser"
const val getLocalUsersCommandPrefix = "getLocalUsers"
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

            command.startsWith(getLocalUsersCommandPrefix) -> {
                val result = handler.HandleGetAllLocalUsers()
                println("Users info: $result")
            }

            else -> println("You entered: $command")
        }
    }

    println("Goodbye!")
}