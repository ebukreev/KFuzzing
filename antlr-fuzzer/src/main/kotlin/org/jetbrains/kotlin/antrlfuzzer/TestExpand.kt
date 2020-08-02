package org.jetbrains.kotlin.antrlfuzzer

import org.jetbrains.kotlin.cli.common.ExitCode
import org.jetbrains.kotlin.cli.jvm.K2JVMCompiler
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream

val compiler = K2JVMCompiler()

fun main() {
    val first = parse(generateCode())
            /*parse("""
interface Test {
    fun test(): String {
        return "OK"
    }

    fun testAbstract(): String
}
""")*/
    var previousTree = first
    var result: ExitCode
    var expandedCode: String
    var ok = 0
    var bug = 94
    var k = 0
    var flag = false
    while (true) {
        k++
        /*if (k % 3000 == 1) {
            previousTree = first
        }*/
        println("тест номер $k")
        val file = File(".\\get.kt")
        file.createNewFile()
        expandedCode = expandTree(parse(generateCode()))
        file.writeText(expandedCode)
        //println(expandedCode)
        result = checkCode()
        if (result == ExitCode.OK) {
            /*ok++
            val okFile = File(".\\ok\\test$ok.kt")
            okFile.createNewFile()
            okFile.writeText(expandedCode)
            previousTree = parse(expandedCode)*/
            println("TRUE")
        } else if (result == ExitCode.INTERNAL_ERROR || result == ExitCode.SCRIPT_EXECUTION_ERROR) {
            bug++
            val bugFile = File(".\\bugs\\test$bug.kt")
            bugFile.createNewFile()
            bugFile.writeText(expandedCode)
            println("BUG!!!")
        } else {
            println("COMPILATION_ERROR")
        }
        file.delete()
    }
}

val ps = /*System.out*/ PrintStream(ByteArrayOutputStream())
fun checkCode(): ExitCode = compiler.exec(ps,
    ".\\get.kt", "-nowarn", "-kotlin-home",
    "C:\\Users\\HP\\AppData\\Roaming\\JetBrains\\IntelliJIdea2020.1\\plugins\\Kotlin")