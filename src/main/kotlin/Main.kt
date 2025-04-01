package org.example

const val getUserInfoCommandPrefix = "getUser"
const val exitCommandPrefix = "exit"

fun main() {
    println("Welcome to my github cli - enter help to see command details")

    while (true) {
        print("> ")
        val input = readlnOrNull()
        val command = input?.trim()

        when {
            command.isNullOrBlank() -> continue
            command.startsWith(exitCommandPrefix) -> break
            command.startsWith(getUserInfoCommandPrefix) -> continue
            else -> println("You entered: $command")
        }
    }

    println("Goodbye!")
}