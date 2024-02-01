import java.util.HashSet;

/**
 * Node est une classe abstraite représentant un nœud dans un arbre de syntaxe abstraite (AST).
 * Les nœuds de cet arbre sont utilisés pour évaluer des expressions ou des formules dans le contexte de l'application.
 *
 * @author [Baptiste BLANCHON, Ayoub AMMARA & Samet DURAN]
 * @version 1.0
 */

public abstract class Node {

    /**
     * Évalue la valeur du nœud dans le contexte de l'arbre de syntaxe abstraite.
     * Cette méthode est abstraite et doit être implémentée par des sous-classes pour gérer l'évaluation spécifique
     * des différents types de nœuds (par exemple, les nœuds représentant des opérateurs, des nombres, ou des
     * références de cellules).
     *
     * @param visited Un ensemble de références de cellules déjà visitées. Cela est utilisé pour détecter des
     *                références circulaires.
     * @return Le résultat de l'évaluation du nœud.
     * @throws IncalculableFormulaException Si la formule ne peut pas être calculée.
     * @throws IncorrectFormulaException    Si la formule est incorrecte.
     */
    public abstract double evaluate(HashSet<String> visited) throws IncalculableFormulaException, IncorrectFormulaException;
}