/**
 * IncorrectFormulaException est une exception personnalisée indiquant qu'une formule est syntaxiquement ou
 * structurellement incorrecte.
 * Cette exception est typiquement levée lors de la validation ou de l'évaluation de formules dans un tableur ou
 * un système similaire.
 *
 * @author [Baptiste BLANCHON, Ayoub AMMARA & Samet DURAN]
 * @version 1.0
 */

public class IncorrectFormulaException extends Exception {

    /**
     * Constructeur pour IncorrectFormulaException.
     * Initialise une nouvelle exception avec un message spécifiant le problème avec la formule.
     *
     * @param message Le message d'erreur détaillant la nature de l'incorrectitude de la formule.
     */
    public IncorrectFormulaException(String message) {
        super(message);
    }
}