package org.jetbrains.kotlin.antrlfuzzer


import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.tool.ast.GrammarAST
import kotlin.random.Random

fun expandTree(tree: ParserRuleContext): String {
    val (lexer, parser) = loadGrammar(
        lexerGrammarPath = "./grammar/KotlinLexer.g4",
        parserGrammarPath = "./grammar/KotlinParser.g4"
    )

    val rulesCollector = GrammarRulesCollector(lexer, parser)

    val (lexerRules, parserRules) = rulesCollector.getRules()

    val codegen = CodeGen(
        lexerRules,
        parserRules,
        rulesCollector.getBaseRule(),
        maxDepth = 2
    )

    val code = Code()

    val rn = KotlinParser.ruleNames

    fun nodeToString(node: ParseTree) {
        if (node.childCount == 0) {
            code.append(node.text)
        }
    }
    
    var parentGrammar: GrammarAST = rulesCollector.getBaseRule()

    fun expand(tree: ParseTree) {
        if (tree.childCount == 0) {
            return
        }
        if (tree is ParserRuleContext) {
            parentGrammar = rulesCollector.getBaseRule(rn[tree.ruleIndex])
            //println(rn[tree.ruleIndex])
        }
        for (i in 0 until tree.childCount) {
            nodeToString(tree.getChild(i))
            if (Random.nextInt(128) == 1) {
                code.append(codegen.gen(parentGrammar))
                codegen.code = Code()
            }
            val next = (tree.getChild(i) as? ParserRuleContext) ?: tree.getChild(i)
            expand(next)
        }
    }
    expand(tree)

    return code.code.toString().replace("<EOF>", "")
}