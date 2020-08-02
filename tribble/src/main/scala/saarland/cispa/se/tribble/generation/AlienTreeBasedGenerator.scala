package saarland.cispa.se.tribble.generation

import java.io.File
import java.nio.file.{Files, Paths}

import saarland.cispa.se.tribble.input.{GrammarLoader, _}
import saarland.cispa.se.tribble.model.{DTree, Literal}

import scala.util.Random

object AlienTreeBasedGenerator {
  def expandTree(tree: DTree): DTree = {
    val automatonDir = Files.createDirectories(Paths.get("automata"))
    val automatonCache = new AutomatonCache(new File(automatonDir.toString))
    val modelAssembler = new ModelAssembler(10, automatonCache, Double.MinPositiveValue, 1.0d)
    val loader = new GrammarLoader(modelAssembler, automatonCache)
    val kGrammar = loader.loadGrammar(new File(".\\src\\main\\resources\\Kotlin.scala"))
    val generator = new ShortestTreeGenerator(
      new RegexGenerator(new Random(new Random().nextInt()), 1),
      new Random(new Random().nextInt()), 2)
    for (node <- tree.nodes) {
      if (Random.nextInt(64) == 1) {
        val tree = generator.gen(kGrammar.root, Option(node), 1)(kGrammar)
        node.children.update(node.children.size, tree)
      }
    }
    tree
  }
}
