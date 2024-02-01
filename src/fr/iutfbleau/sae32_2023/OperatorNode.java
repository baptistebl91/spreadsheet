import java.util.HashSet;

/**
 * OperatorNode est une sous-classe de Node qui représente un opérateur arithmétique dans un arbre de
 * syntaxe abstraite (AST).
 * Elle contient des références à des nœuds gauche et droit et applique un opérateur arithmétique à leurs évaluations.
 *
 * @version 1.0
 */

public class OperatorNode extends Node {
    char operator;
    Node left;
    Node right;

    /**
     * Constructeur pour OperatorNode.
     * Initialise un nœud avec un opérateur spécifique et des nœuds enfants gauche et droit.
     *
     * @param operator L'opérateur arithmétique que ce nœud représente (par exemple '+', '-', '*', '/').
     * @param left     Le nœud enfant gauche.
     * @param right    Le nœud enfant droit.
     */
    OperatorNode(char operator, Node left, Node right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    /**
     * Évalue l'opération arithmétique représentée par ce nœud.
     * Utilise l'opérateur stocké dans ce nœud pour appliquer une opération arithmétique aux résultats évalués
     * des nœuds enfants gauche et droit. Cette méthode gère également les cas spéciaux tels que la division par zéro.
     *
     * @param visited Un ensemble de références de cellules déjà visitées, utilisé pour détecter les références
     *                circulaires.
     * @return Le résultat de l'opération arithmétique.
     * @throws IncalculableFormulaException Si une opération ne peut pas être calculée (comme une division par zéro).
     * @throws IncorrectFormulaException    Si un opérateur inconnu est utilisé.
     */
    @Override
    public double evaluate(HashSet<String> visited) throws IncalculableFormulaException, IncorrectFormulaException {
        switch (operator) {
            case '+':
                return left.evaluate(visited) + right.evaluate(visited);
            case '-':
                return left.evaluate(visited) - right.evaluate(visited);
            case '*':
                return left.evaluate(visited) * right.evaluate(visited);
            case '/':
                double leftResult = left.evaluate(visited);
                double rightResult = right.evaluate(visited);
                if (rightResult == 0) {
                    throw new IncalculableFormulaException("Formule incalculable : division par 0.");
                }
                return leftResult / rightResult;

            default:
                throw new IncorrectFormulaException("Formule incorrecte : opérateur inconnu.");
        }
    }
}
