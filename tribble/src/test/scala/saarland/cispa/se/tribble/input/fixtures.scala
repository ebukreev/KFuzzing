package saarland.cispa.se.tribble.input

import java.io.File
import java.nio.file.{Files, Paths}


trait SharedAutomatonCache {
  private val automatonDir = Files.createDirectories(Paths.get("automata"))
  val automatonCache = new AutomatonCache(new File(automatonDir.toString))
}

trait SharedModelAssembler extends SharedAutomatonCache {
  val modelAssembler = new ModelAssembler(10, automatonCache, Double.MinPositiveValue, 1.0d)
}
