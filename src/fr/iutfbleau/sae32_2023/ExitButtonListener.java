import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ExitButtonListener est un ActionListener qui gère l'action de sortie de l'application.
 * Lorsqu'un événement est déclenché, cette classe exécute l'action de fermeture de l'application.
 *
 * @version 1.0
 */
public class ExitButtonListener implements ActionListener {

    /**
     * Gère l'action de fermeture de l'application.
     * Cette méthode est appelée lorsque l'événement associé à ce listener est déclenché,
     * par exemple, lorsqu'un utilisateur clique sur le bouton "Quitter".
     *
     * @param e L'événement d'action qui a déclenché cette méthode.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        System.exit(0);
    }
}
