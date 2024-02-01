import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * La classe AbstractSyntaxTree est utilisée pour construire et évaluer un arbre de syntaxe abstraite
 * * afin de représenter des formules de cellules dans un tableur. Cette classe gère les opérations arithmétiques de base
 * * et permet la référence à d'autres cellules dans une grille. Elle utilise une expression préfixée pour évaluer
 * * les formules et détecter les erreurs comme les références circulaires ou les formules mal formées.
 *
 * @version 1.0
 */

public class AbstractSyntaxTree {
    private final Node root;
    private final String[] validOperators = new String[]{"+", "-", "*", "/"};
    private final String originCellReference;
    private final AbstractSyntaxTree[][] abstractSyntaxTreeArray;

    private final HashSet<String> dependentCells = new HashSet<>();

    /**
     * Construit un arbre de syntaxe abstraite à partir d'une référence de cellule, d'une formule et d'un tableau
     * d'arbres de syntaxe abstraite.
     *
     * @param cellReference           La référence de la cellule pour laquelle l'arbre est construit.
     * @param formula                 La formule à analyser et évaluer.
     * @param abstractSyntaxTreeArray Le tableau des arbres de syntaxe abstraite existants.
     * @throws IncorrectFormulaException Si la formule n'est pas syntaxiquement correcte.
     */
    public AbstractSyntaxTree(String cellReference, String formula, AbstractSyntaxTree[][] abstractSyntaxTreeArray) throws IncorrectFormulaException {
        this.originCellReference = cellReference;
        this.abstractSyntaxTreeArray = abstractSyntaxTreeArray;
        LinkedList<String> parts = new LinkedList<>(Arrays.asList(formula.split(" ")));

        if (!isValidPrefixExpression(parts)) {
            throw new IncorrectFormulaException("Formule incorrecte : mauvaise syntaxe.");
        }

        this.root = buildTree(parts);
    }

    /**
     * Évalue l'expression de l'arbre de syntaxe abstraite.
     *
     * @param visited Ensemble de références de cellules déjà visitées pour détecter les références circulaires.
     * @return Le résultat de l'évaluation de l'expression.
     * @throws IncalculableFormulaException Si l'expression ne peut pas être calculée.
     * @throws IncorrectFormulaException    Si l'expression est syntaxiquement incorrecte.
     */
    double evaluate(HashSet<String> visited) throws IncalculableFormulaException, IncorrectFormulaException {
        visited.add(originCellReference);
        return root.evaluate(visited);
    }

    /**
     * Vérifie si l'expression préfixée est valide.
     *
     * @param parts Les parties de l'expression préfixée.
     * @return Vrai si l'expression est valide, faux sinon.
     */
    private boolean isValidPrefixExpression(LinkedList<String> parts) {
        if (parts.isEmpty()) {
            return false;
        }
        return isValidPrefixExpression(parts, 0) == parts.size();
    }

    /**
     * Aide à valider l'expression préfixée en vérifiant chaque partie de l'expression.
     *
     * @param parts Les parties de l'expression préfixée.
     * @param index L'index actuel dans la liste des parties.
     * @return L'index suivant dans la liste si la partie actuelle est valide, -1 sinon.
     */
    private int isValidPrefixExpression(LinkedList<String> parts, int index) {
        if (index >= parts.size()) {
            return -1; // Retourne -1 si l'index est hors de la liste
        }

        String token = parts.get(index);

        if (isValidOperator(token)) {
            int rightIndex = isValidPrefixExpression(parts, index + 1);
            if (rightIndex == -1) {
                return -1; // Première opérande invalide
            }
            return isValidPrefixExpression(parts, rightIndex);
        } else if (isNumber(token) || isValidCell(token)) {
            return index + 1;
        } else {
            return -1;
        }
    }

    /**
     * Vérifie si une chaîne de caractères est un opérateur valide.
     *
     * @param part La chaîne de caractères à vérifier.
     * @return Vrai si la chaîne est un opérateur valide, faux sinon.
     */
    private boolean isValidOperator(String part) {
        return Arrays.asList(validOperators).contains(part);
    }

    /**
     * Vérifie si une chaîne de caractères est une réference de cellule valide.
     *
     * @param part La chaîne de caractères à vérifier.
     * @return Vrai si la chaîne est une référence de cellule valide, faux sinon.
     */
    private boolean isValidCell(String part) {
        if (part.length() != 2) {
            return false;
        }

        char column = part.charAt(0);
        if (column < 'A' || column > 'I') {
            return false;
        }

        char row = part.charAt(1);
        return row >= '1' && row <= '9';
    }

    /**
     * Vérifie si une chaîne de caractères représente un nombre.
     *
     * @param part La chaîne de caractères à vérifier.
     * @return Vrai si la chaîne représente un nombre, faux sinon.
     */
    private boolean isNumber(String part) {
        try {
            Double.parseDouble(part);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Construit l'arbre de syntaxe abstraite en utilisant les parties de l'expression préfixée.
     *
     * @param parts Les parties de l'expression préfixée.
     * @return La racine de l'arbre de syntaxe abstraite.
     * @throws IncorrectFormulaException Si la formule est incorrecte.
     */
    private Node buildTree(LinkedList<String> parts) throws IncorrectFormulaException {
        if (parts.isEmpty()) {
            throw new IncorrectFormulaException("Formule incorrecte : référence de cellule ou nombre manquant.");
        }
        String part = parts.poll();
        if (isValidOperator(part)) {
            return new OperatorNode(part.charAt(0), buildTree(parts), buildTree(parts));
        } else if (isValidCell(part)) {
            return new CellNode(part, this);
        } else {
            try {
                return new NumberNode(Double.parseDouble(part));
            } catch (NumberFormatException exception) {
                throw new IncorrectFormulaException("Formule incorrecte : " + part + " n'a pas pu être converti en double.");
            }
        }
    }

    public void addDependentCell(String cellReference) {
        this.dependentCells.add(cellReference);
    }

    public HashSet<String> getDependentCells() {
        return this.dependentCells;
    }

    public AbstractSyntaxTree[][] getAbstractSyntaxTreeArray() {
        return this.abstractSyntaxTreeArray;
    }

    public String getOriginCellReference() {
        return originCellReference;
    }
}
