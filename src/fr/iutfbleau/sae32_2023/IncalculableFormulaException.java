/**
 * IncalculableFormulaException est une exception personnalisée qui indique qu'une formule dans le tableur ne peut pas
 * être calculée.
 * Cette exception peut être levée en cas d'erreurs dans la formule ou de problèmes lors de l'évaluation de celle-ci.
 *
 * @author [Baptiste BLANCHON, Ayoub AMMARA & Samet DURAN]
 * @version 1.0
 */

public class IncalculableFormulaException extends Exception {

    /**
     * Constructeur pour IncalculableFormulaException.
     * Initialise une nouvelle exception avec un message détaillé spécifiant la raison pour laquelle la formule
     * ne peut pas être calculée.
     *
     * @param message Le message détaillant la raison de l'exception.
     */
    public IncalculableFormulaException(String message) {
        super(message);
    }
}