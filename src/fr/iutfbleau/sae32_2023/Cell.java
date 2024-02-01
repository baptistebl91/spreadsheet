import java.util.Observable;

/**
 * La classe Cell représente une cellule individuelle dans notre tableur.
 * Elle étend la classe Observable, permettant à d'autres composants de recevoir des notifications
 * lors de la modification de la formule de la cellule.
 *
 * @version 1.0
 */
public class Cell extends Observable {

    private final int row;
    private final int col;
    private final String cellReference;

    private String formula = "";

    /**
     * Constructeur de la classe Cell.
     * Initialise une cellule avec ses coordonnées (ligne et colonne), et calcule sa référence de cellule.
     *
     * @param row La ligne où se trouve la cellule.
     * @param col La colonne où se trouve la cellule.
     */
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.cellReference = CellOperations.getCellReferenceFromCoordinates(row, col);
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public String getCellReference() {
        return cellReference;
    }

    public String getFormula() {
        return formula;
    }

    /**
     * Définit la formule de la cellule et notifie les observateurs du changement.
     * Cette méthode marque la cellule comme ayant été modifiée et notifie tous ses observateurs
     * qu'une mise à jour est nécessaire. Cela est crucial pour le recalcul des valeurs dans les cellules dépendantes.
     *
     * @param formula La nouvelle formule à attribuer à la cellule.
     */
    public void setFormula(String formula) {
        this.formula = formula;
        setChanged();
        notifyObservers(this);
    }
}
