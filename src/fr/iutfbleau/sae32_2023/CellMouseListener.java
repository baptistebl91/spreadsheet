import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * CellMouseListener étend MouseAdapter pour gérer les interactions de la souris avec les cellules d'un tableur.
 * Il modifie l'apparence des cellules lors du survol et du clic, et met à jour la barre de formule correspondante.
 *
 * @version 1.0
 */
public class CellMouseListener extends MouseAdapter {
    private final TabPage tabPage;
    private final int row;
    private final int col;

    /**
     * Constructeur de CellMouseListener.
     *
     * @param tabPage La page associée contenant les cellules.
     * @param row     La ligne de la cellule sur laquelle le MouseListener est appliqué.
     * @param col     La colonne de la cellule sur laquelle le MouseListener est appliqué.
     */
    public CellMouseListener(TabPage tabPage, int row, int col) {
        this.tabPage = tabPage;
        this.row = row;
        this.col = col;
    }

    /**
     * Gère l'événement de la souris entrant dans la zone de la cellule.
     * Modifie l'apparence de la cellule pour indiquer qu'elle est survolée par la souris.
     *
     * @param e L'événement de la souris.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        CellLabel cellLabel = (CellLabel) e.getSource();
        cellLabel.setMouseOnColor();
    }

    /**
     * Gère l'événement de la souris sortant de la zone de la cellule.
     * Réinitialise l'apparence de la cellule à son état par défaut.
     *
     * @param e L'événement de la souris.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        CellLabel cellLabel = (CellLabel) e.getSource();
        cellLabel.setMouseOutColor();
    }

    /**
     * Gère l'événement du clic de souris sur la cellule.
     * Met en évidence la cellule sélectionnée et met à jour la barre de formule avec la formule de la cellule.
     *
     * @param e L'événement du clic de souris.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Case sélectionnée: " + CellOperations.getCellReferenceFromCoordinates(row, col));

        // Vérifier si un JLabel est déjà cliqué. Si oui, on remet la bordure à son apparence par défaut.
        if (tabPage.selectedCellLabel != null) {
            tabPage.selectedCellLabel.setDefaultAppearance();
        }

        CellLabel cellLabel = (CellLabel) e.getSource();
        cellLabel.setBorder(BorderFactory.createLineBorder(new Color(0, 100, 0), 3));

        // Mise à jour de la nouvelle case actuellement sélectionnée et de ses coordonnées.
        tabPage.selectedCellLabel = cellLabel;

        tabPage.formulaBar.setText(cellLabel.getCell().getFormula());
    }
}
