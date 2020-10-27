package tw.racket.lang

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import tw.racket.lang.lexer.RacketLexerAdapter
import tw.racket.lang.parser.RacketParser
import tw.racket.lang.psi.RacketFile
import tw.racket.lang.psi.RacketTypes

class RacketParserDefinition : ParserDefinition {
    override fun createLexer(project: Project): Lexer {
        return RacketLexerAdapter()
    }

    override fun createParser(project: Project): PsiParser {
        return RacketParser()
    }

    override fun getFileNodeType(): IFileElementType {
        return FILE
    }

    override fun getWhitespaceTokens(): TokenSet {
        return WHITE_SPACES
    }

    override fun getCommentTokens(): TokenSet {
        return COMMENTS
    }

    override fun getStringLiteralElements(): TokenSet {
        return TokenSet.EMPTY
    }

    override fun createElement(astNode: ASTNode): PsiElement {
        return RacketTypes.Factory.createElement(astNode)
    }

    override fun createFile(fileViewProvider: FileViewProvider): PsiFile {
        return RacketFile(fileViewProvider)
    }

    companion object {
        val WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE)
        val COMMENTS = TokenSet.create(RacketTypes.COMMENT)
        val FILE = IFileElementType(RacketLanguage)
    }
}