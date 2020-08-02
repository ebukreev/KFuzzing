package saarland.cispa.se.tribble

import java.io.{ByteArrayOutputStream, File, FileWriter, PrintStream, PrintWriter}

import org.jetbrains.kotlin.cli.common.ExitCode
import org.jetbrains.kotlin.cli.jvm.K2JVMCompiler
import saarland.cispa.se.tribble.generation.{AlienTreeBasedGenerator, TreeFromKtGenerator}
import saarland.cispa.se.tribble.input.KtParser
import saarland.cispa.se.tribble.model.DNode

import scala.io.Source
import util.control.Breaks._

object CompilerTest {
  def main(args: Array[String]): Unit = {
    val testFile: File = new File(".\\testIn\\testIn(1).kt")
    test(testFile, 10000)
      //test(new File(".\\testOK\\" + testFile.getName + i + ".kt"), 10)
  }

  def test(inputFile: File, num: Int): Int = {
    val compiler: K2JVMCompiler = new K2JVMCompiler()
    val root = DNode(null, None)
    val source = Source.fromFile(inputFile)
    val code = source.mkString
    source.close()
    val unchanged: String = "package lib \n \n  inline fun Int . toString ( ignored : Int ) = toString ( )  "
    /*TreeFromKtGenerator
      .generateTree(KtParser.parse(code), root).leaves
      .map(_.value + " ").mkString
      .replaceAll("<EOF>", "")*/
    var okCount: Int = 0
    var bugsCount: Int = 0
    for (i <- 1 to num) {
      val testFile: File = new File(".\\testfile.kt")
      testFile.createNewFile()
      val newCode: String = AlienTreeBasedGenerator
        .expandTree(
          TreeFromKtGenerator
            .generateTree(KtParser.parse(code), root)).leaves
        .map(_.value + " ").mkString
        .replaceAll("<EOF>", "")
      breakable {
        if (newCode.equals(unchanged)) {
          testFile.delete()
          break
        }
      }
      val pw: PrintWriter = new PrintWriter(testFile)
      pw.write(newCode)
      pw.flush()
      pw.close()
      val result: ExitCode = compiler.exec(System.out/*new PrintStream(new ByteArrayOutputStream())*/,
        ".\\testfile.kt", "-nowarn", "-kotlin-home", "C:\\Users\\HP\\AppData\\Roaming\\JetBrains\\IntelliJIdea2020.1\\plugins\\Kotlin")
      if (result == ExitCode.OK) {
        okCount += 1
        val file: File = new File(".\\testOK\\" + inputFile.getName + okCount + ".kt")
        file.createNewFile()
        val codeWriter: FileWriter = new FileWriter(file)
        codeWriter.write(newCode)
        codeWriter.flush()
        codeWriter.close()
      } else if (result == ExitCode.INTERNAL_ERROR
        || result == ExitCode.SCRIPT_EXECUTION_ERROR) {
        bugsCount += 1
        val file: File = new File(".\\bugs\\" + inputFile.getName + bugsCount + ".kt")
        file.createNewFile()
        val codeWriter: FileWriter = new FileWriter(file)
        codeWriter.write(newCode)
        codeWriter.flush()
        codeWriter.close()
      }
      if (i < num) {
        testFile.delete()
      }
    }
    okCount
  }
}
