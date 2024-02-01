import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe SheetAccessButtonListener implémente l'interface ActionListener.
 * Elle est conçue pour écouter les événements de clic sur un bouton et déclencher
 * un changement de vue dans l'interface utilisateur.
 *
 * @version 1.0
 */

public class SheetAccessButtonListener implements ActionListener {

    private final HomePage homePage;

    /**
     * Constructeur pour SheetAccessButtonListener.
     *
     * @param homePage Une référence à l'objet HomePage qui contient le layout et les vues.
     *                 Cette référence est utilisée pour manipuler les vues de l'interface utilisateur.
     */
    public SheetAccessButtonListener(HomePage homePage) {
        this.homePage = homePage;
    }

    /**
     * Méthode appelée lorsqu'une action se produit (par exemple, un clic sur un bouton).
     * Cette méthode déclenche un changement de vue dans la HomePage.
     *
     * @param e L'événement d'action qui contient les informations sur l'événement déclenché.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        this.homePage.cardLayout.show(this.homePage.getContentPane(), "TabPage");

    }
}
