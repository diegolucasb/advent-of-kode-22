package utils

import java.io.File

fun readInput(name: String) = File("${System.getProperty("user.dir")}/src/main/resources", "$name.txt")
    .readLines()