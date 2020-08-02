package org.jetbrains.kotlin.antrlfuzzer

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.ParseTree

fun parse(code: String): ParserRuleContext {
    val lexer = KotlinLexer(CharStreams.fromString(code))
    val parser = KotlinParser(CommonTokenStream(lexer))
    return parser.kotlinFile()
}