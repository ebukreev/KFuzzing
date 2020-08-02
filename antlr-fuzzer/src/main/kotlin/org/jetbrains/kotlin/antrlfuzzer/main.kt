package org.jetbrains.kotlin.antrlfuzzer

import org.jetbrains.kotlin.cli.common.ExitCode
import org.jetbrains.kotlin.cli.jvm.K2JVMCompiler
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileWriter
import java.io.PrintStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

fun generateCode(): String {
    val (lexer, parser) = loadGrammar(
        lexerGrammarPath = "./grammar/KotlinLexer.g4",
        parserGrammarPath = "./grammar/KotlinParser.g4"
    )

    val rulesCollector = GrammarRulesCollector(lexer, parser)

    val (lexerRules, parserRules) = rulesCollector.getRules()

   /* println("!!!")
    println(lexerRules)*/

    val codegen = CodeGen(
        lexerRules,
        parserRules,
        rulesCollector.getBaseRule(),
        maxDepth = 3
    )

    return codegen.gen()

//    val t = ((productionsLexerMap["AtIdentifier"]!!.children[0]!! as GrammarAST).children[1] as GrammarAST).text
//
//    println(productionsLexerMap[t])
//    println(((productionsMap["annotation"]!!.children[0]!! as GrammarAST).children[0] as GrammarAST).children)
}

//            print(" ".repeat(level))
//                println("${it.type} — ${it.text}")

fun main() {
    val compiler = K2JVMCompiler()
//    val testFile = File(".\\buglist.txt")
//    val fw = FileWriter(testFile, true)
//    for (i in 1..25923) {
//        println(i)

    compiler.exec(System.out,
            "..\\KotlinFuzzer\\build\\libs\\production\\kotlin_tests\\Test_1\\Test_1.kt", "-nowarn", "-kotlin-home",
            "C:\\Users\\HP\\AppData\\Roaming\\JetBrains\\IntelliJIdea2020.1\\plugins\\Kotlin")
//        if (result == ExitCode.OK) {
//            println("TRUE")
//        } else if (result == ExitCode.INTERNAL_ERROR || result == ExitCode.SCRIPT_EXECUTION_ERROR) {
//            println("BUG!!!!!!!!!!!!!!!!!!")
//            fw.write(i)
//            fw.write("\n")
//            fw.flush()
//        }
//    }
//    fw.close()
//    val compiler = K2JVMCompiler()
//    for (i in 1..107) {
//      if (compiler.exec(
//            System.out, ".\\bugs\\test$i.kt", "-nowarn", "-kotlin-home",
//            "C:\\Users\\HP\\AppData\\Roaming\\JetBrains\\IntelliJIdea2020.1\\plugins\\Kotlin"
//        ) != ExitCode.INTERNAL_ERROR) {
//          Files.delete(Path.of(".\\bugs\\test$i.kt"))
//      }
//    }
//    val (lexer, parser) = loadGrammar(
//            lexerGrammarPath = "./grammar/KotlinLexer.g4",
//            parserGrammarPath = "./grammar/KotlinParser.g4"
//    )
//    val rulesCollector = GrammarRulesCollector(lexer, parser)
//
//    val (lexerRules, parserRules) = rulesCollector.getRules()
//
//    val codegen = CodeGen(
//            lexerRules,
//            parserRules,
//            rulesCollector.getBaseRule(),
//            maxDepth = 2
//    )
//    repeat(30) {
//        println(codegen.gen(rulesCollector.getBaseRule("ifExpression"), 2))
//        codegen.code = Code()
//    }
  /*  val previousTree = parse("""
fun testPrefix() {
    operator fun Any.not() {}
    !todo()
}

fun testPostfixWithCall(n: Nothing) {
    operator fun Nothing.inc(): Nothing = this
    n++
}

fun testPostfixSpecial() {
    todo()
}

fun todo(): Nothing = throw Exception()
""")
    print(previousTree.getChild(2).toStringTree())*/
/*    repeat(10) {
        println(generateCode())
        println("NEW!!!!!!!!!!!!")
    }*/
}