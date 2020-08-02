package saarland.cispa.se.tribble.generation

import org.antlr.v4.runtime.tree.ParseTree
import saarland.cispa.se.tribble.input.KtParser
import saarland.cispa.se.tribble.model.{DLeaf, DNode, DTree, Regex}

object TreeFromKtGenerator {
  def generateTree(element: ParseTree, root: DNode): DNode = {
    for (i <- 0 until element.getChildCount) {
      if (element.getChild(i).getChildCount == 0) {
        root.children(i) = DLeaf(null, Some(root), element.getChild(i).getText)
      } else {
        val node = DNode(null, Some(root))
        root.children(i) = node
        generateTree(element.getChild(i), node)
      }
    }
    root
  }
}
