package saarland.cispa.se.tribble

import java.io._

import saarland.cispa.se.tribble.generation.{AlienTreeBasedGenerator, TreeFromKtGenerator}
import saarland.cispa.se.tribble.input.KtParser
import saarland.cispa.se.tribble.model.DNode

import scala.io.Source

/*
object KtGenerator {
  def main(args: Array[String]): Unit = {
    for (i <- 1 to 1) {
      val source = Source.fromFile(".\\testIn\\testIn(" + i + ").kt")
      val code = source.mkString
      source.close()
      for (j <- 1 to 20) {
        val root = DNode(null, None)
        val pw = new PrintWriter(new File(".\\testM\\testIn(" + i + ")" + j + ".kt"))
        pw.write(
          AlienTreeBasedGenerator
            .expandTree(
              TreeFromKtGenerator
                .generateTree(KtParser.parse(code), root)).leaves
            .map(_.value + " ").mkString
            .replaceAll("<EOF>", ""))
        pw.close()
      }
    }
  }
}
*/
