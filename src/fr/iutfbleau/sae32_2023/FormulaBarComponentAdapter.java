import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * FormulaBarComponentAdapter est un adaptateur de composants spécialement conçu pour gérer les événements
 * liés à l'affichage d'une barre de formule dans une interface utilisateur.
 *
 * @version 1.0
 */

public class FormulaBarComponentAdapter extends ComponentAdapter {

    private final JTextField formulaBar;

    /**
     * Constructeur de FormulaBarComponentAdapter.
     *
     * @param formulaBar Le JTextField qui agit en tant que barre de formule et nécessite une gestion des événements.
     */
    public FormulaBarComponentAdapter(JTextField formulaBar) {
        this.formulaBar = formulaBar;
    }

    /**
     * Gère l'événement de l'affichage du composant.
     * Lorsque la barre de formule devient visible, cette méthode s'assure qu'elle reçoit le focus immédiatement,
     * permettant à l'utilisateur de commencer à saisir une formule sans devoir cliquer manuellement sur la barre.
     *
     * @param e L'événement de composant qui a déclenché cette méthode.
     */
    @Override
    public void componentShown(ComponentEvent e) {
        formulaBar.requestFocusInWindow();
    }
}
