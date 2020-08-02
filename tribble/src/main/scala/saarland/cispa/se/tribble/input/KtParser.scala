package saarland.cispa.se.tribble.input

import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}
import ru.spbstu.kfuzz.{KotlinLexer, KotlinParser}


object KtParser {
  def parse(code: String): ParseTree = {
    val lexer = new KotlinLexer(CharStreams.fromString(code))
    val parser = new KotlinParser(new CommonTokenStream(lexer))
    parser.kotlinFile
  }
}
