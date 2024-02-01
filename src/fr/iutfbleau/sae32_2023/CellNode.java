import java.util.HashSet;

/**
 * CellNode représente un nœud dans un arbre de syntaxe abstraite (AST) qui fait référence à une cellule d'un tableur.
 * Il est utilisé pour évaluer la valeur de cette cellule dans le contexte de l'AST.
 *
 * @version 1.0
 */
public class CellNode extends Node {
    String cellReference;
    AbstractSyntaxTree fatherAst;

    /**
     * Constructeur de CellNode.
     *
     * @param cellReference La référence de la cellule que ce nœud représente.
     * @param fatherAst     L'arbre de syntaxe abstraite parent dans lequel ce nœud est contenu.
     */
    CellNode(String cellReference, AbstractSyntaxTree fatherAst) {
        this.cellReference = cellReference;
        this.fatherAst = fatherAst;
    }

    /**
     * Évalue la valeur de la cellule référencée par ce nœud.
     * Cette méthode vérifie également les références circulaires et les cellules vides, lançant des exceptions
     * si nécessaire.
     *
     * @param visited Un ensemble de références de cellules déjà visitées pour détecter les références circulaires.
     * @return La valeur évaluée de la cellule.
     * @throws IncorrectFormulaException    Si une référence circulaire est détectée.
     * @throws IncalculableFormulaException Si la cellule référencée est vide ou ne peut pas être évaluée.
     */
    @Override
    public double evaluate(HashSet<String> visited) throws IncorrectFormulaException, IncalculableFormulaException {

        if (visited.contains(cellReference)) {
            throw new IncorrectFormulaException("Formule incorrecte : référence circulaire détectée dans la cellule " + cellReference);
        }
        visited.add(cellReference);

        int row = CellOperations.getRowFromCellReference(cellReference);
        int col = CellOperations.getColFromCellReference(cellReference);
        System.out.println("Récupération de l'AST de " + cellReference);
        AbstractSyntaxTree abstractSyntaxTree = this.fatherAst.getAbstractSyntaxTreeArray()[row][col];

        if (abstractSyntaxTree == null) {
            throw new IncalculableFormulaException("Formule incalculable : la cellule " + cellReference + " est vide.");
        }

        fatherAst.addDependentCell(cellReference);

        return abstractSyntaxTree.evaluate(visited);
    }
}
