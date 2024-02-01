import java.util.HashSet;

/**
 * NumberNode est une sous-classe concrète de Node qui représente un nombre dans un arbre de syntaxe abstraite (AST).
 * Ce type de nœud est utilisé pour stocker et évaluer des valeurs numériques dans le contexte de l'AST.
 *
 * @author [Baptiste BLANCHON, Ayoub AMMARA & Samet DURAN]
 * @version 1.0
 */
public class NumberNode extends Node {
    double value;

    /**
     * Constructeur pour NumberNode.
     * Initialise un nouveau nœud avec une valeur numérique spécifique.
     *
     * @param value La valeur numérique que ce nœud représente.
     */
    NumberNode(double value) {
        this.value = value;
    }

    /**
     * Évalue ce nœud et retourne sa valeur numérique.
     * Dans le cas de NumberNode, cette méthode retourne simplement la valeur numérique stockée,
     * car il n'y a pas de calcul supplémentaire nécessaire.
     *
     * @param visited Un ensemble de références de cellules déjà visitées. Non utilisé dans cette implémentation.
     * @return La valeur numérique de ce nœud.
     */
    @Override
    public double evaluate(HashSet<String> visited) {
        return value;
    }
}