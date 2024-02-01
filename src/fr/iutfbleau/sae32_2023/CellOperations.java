/**
 * La classe CellOperations fournit des méthodes statiques pour effectuer la conversion entre les références de
 * cellules et leurs coordonnées correspondantes dans un tableur.
 *
 * @version 1.0
 */

public class CellOperations {

    /**
     * Convertit une référence de cellule en sa colonne correspondante.
     * Par exemple, 'A' est converti en 0, 'B' en 1, etc.
     *
     * @param cellReference La référence de la cellule sous forme de chaîne de caractères.
     * @return L'index de la colonne correspondant à la référence de la cellule.
     */
    public static int getColFromCellReference(String cellReference) {
        return (cellReference.charAt(0) - (int) 'A');
    }

    /**
     * Convertit une référence de cellule en sa ligne correspondante.
     * Par exemple, le deuxième caractère '1' est converti en 0, '2' en 1, etc.
     *
     * @param cellReference La référence de la cellule sous forme de chaîne de caractères.
     * @return L'index de la ligne correspondant à la référence de la cellule.
     */
    public static int getRowFromCellReference(String cellReference) {
        return (cellReference.charAt(1) - (int) '1');
    }

    /**
     * Convertit les coordonnées de ligne et de colonne en une référence de cellule.
     * Par exemple, la ligne 0 et la colonne 0 sont converties en 'A1'.
     *
     * @param row L'index de la ligne.
     * @param col L'index de la colonne.
     * @return La référence de la cellule sous forme de chaîne de caractères.
     */
    public static String getCellReferenceFromCoordinates(int row, int col) {
        char colChar = (char) ('A' + col);
        int rowNum = row + 1;
        return colChar + "" + rowNum;
    }
}
