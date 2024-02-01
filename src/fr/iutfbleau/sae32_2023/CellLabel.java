import javax.swing.*;
import java.awt.*;

/**
 * CellLabel étend JLabel, c'est conçu pour représenter visuellement l'état d'une cellule de tableur.
 * Elle affiche différents arrière-plans et bordures en fonction de l'état de la formule de la cellule associée.
 *
 * @version 1.0
 */

public class CellLabel extends JLabel {


    private Cell cell;

    /**
     * Constructeur de CellLabel.
     * Initialise l'étiquette (label) avec une cellule spécifique, définit son apparence par défaut et ses
     * paramètres d'alignement.
     *
     * @param cell La cellule à associer à ce label.
     */
    public CellLabel(Cell cell) {
        this.cell = cell;
        this.setText("");
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setOpaque(true);
        this.setDefaultAppearance();
    }

    /**
     * Définit l'arrière-plan de l'étiquette pour une cellule avec une formule vide.
     * Utilise une couleur spécifique pour indiquer qu'aucune formule n'est associée à la cellule.
     */
    public void setEmptyFormulaBackgroundColor() {
        this.setBackground(Color.GRAY);
    }

    /**
     * Réinitialise l'apparence de l'étiquette à son état par défaut.
     * Utilisé pour rétablir l'apparence standard après tout changement d'état.
     */
    public void setDefaultAppearance() {
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setForeground(Color.BLACK);
    }

    /**
     * Change la couleur de fond pour indiquer une formule calculable.
     * Utilisé pour représenter visuellement qu'une formule peut être évaluée sans erreurs.
     */
    public void setCalculableFormulaColor() {
        this.setBackground(Color.GREEN);
    }

    /**
     * Change la couleur de fond pour indiquer une formule incalculable.
     * Utilisé pour signaler que la formule ne peut pas être évaluée en raison d'erreurs.
     */
    public void setIncalculableFormulaColor() {
        this.setBackground(Color.YELLOW);
    }

    /**
     * Change la couleur de fond pour indiquer une formule incorrecte.
     * Utilisé pour montrer visuellement qu'une formule est syntaxiquement ou structurellement incorrecte.
     */
    public void setIncorrectFormulaColor() {
        this.setBackground(Color.RED);
    }

    /**
     * Change la couleur de fond lorsque la souris survole l'étiquette.
     * Utilisé pour une meilleure expérience utilisateur en indiquant visuellement la cellule active.
     */
    public void setMouseOnColor() {
        this.setBackground(new Color(230, 230, 230));
    }

    /**
     * Réinitialise la couleur de fond lorsque la souris quitte le label.
     * Utilisé pour indiquer que la cellule n'est plus l'élément actif sélectionné.
     */
    public void setMouseOutColor() {
        this.setBackground(Color.WHITE);
    }


    public Cell getCell() {
        return this.cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
}
