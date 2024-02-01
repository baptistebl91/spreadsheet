import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

/**
 * La classe TabPage représente un panneau d'onglet dans une interface utilisateur de type feuille de calcul.
 * Elle implémente l'interface Observer pour suivre les changements dans les cellules de la feuille.
 *
 * @version 1.0
 */

public class TabPage extends JPanel implements Observer {

    // Matrice des étiquettes des cellules.
    public CellLabel[][] cellsLabels = new CellLabel[9][9];

    // Matrice des arbres syntaxiques abstraits pour les formules de chaque cellule.
    public final AbstractSyntaxTree[][] abstractSyntaxTrees = new AbstractSyntaxTree[9][9];

    // Noms des colonnes.
    public final String[] columnsNames = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I"};

    // Barre de formule pour entrer ou modifier des formules de cellules.
    public final JTextField formulaBar;

    // Étiquette de la cellule actuellement sélectionnée.
    public CellLabel selectedCellLabel;

    /**
     * Constructeur pour initialiser le TabPage.
     * Configure la mise en page et ajoute les composants nécessaires.
     */
    public TabPage() {
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        formulaBar = new JTextField();

        // Ajout d'un écouteur pour demander le focus sur le JTextField de la formule.
        this.addComponentListener(new FormulaBarComponentAdapter(formulaBar));

        formulaBar.addActionListener(new FormulaBarListener(this));
        add(formulaBar, BorderLayout.NORTH);

        JPanel gridPanel = createGridPanel();
        add(gridPanel, BorderLayout.CENTER);
    }

    /**
     * Crée un JPanel contenant la grille des cellules et des en-têtes.
     *
     * @return JPanel avec une grille de cellules et des en-têtes.
     */
    private JPanel createGridPanel() {
        JPanel gridPanel = new JPanel(new GridLayout(10, 10));
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if (row == 0 && col > 0) {
                    JLabel headerLabel = createHeaderLabel(columnsNames[col - 1]);
                    gridPanel.add(headerLabel);
                } else if (col == 0 && row > 0) {
                    JLabel headerLabel = createHeaderLabel(Integer.toString(row));
                    gridPanel.add(headerLabel);
                } else if (row > 0) {
                    Cell cell = new Cell(row - 1, col - 1);
                    cell.addObserver(this);
                    CellLabel cellLabel = new CellLabel(cell);
                    cellLabel.addMouseListener(new CellMouseListener(this, row - 1, col - 1));
                    cellsLabels[row - 1][col - 1] = cellLabel;
                    gridPanel.add(cellLabel);
                } else {
                    gridPanel.add(new JLabel(""));
                }
            }
        }
        return gridPanel;
    }

    /**
     * Crée un JLabel pour un en-tête de colonne ou de ligne.
     *
     * @param text Le texte de l'en-tête.
     * @return JLabel configuré pour l'affichage d'en-tête.
     */
    private JLabel createHeaderLabel(String text) {
        JLabel headerLabel = new JLabel(text, SwingConstants.CENTER);
        headerLabel.setOpaque(true);
        headerLabel.setBackground(Color.LIGHT_GRAY);
        headerLabel.setForeground(Color.BLACK);
        headerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return headerLabel;
    }

    /**
     * Méthode appelée lorsqu'une cellule observée est mise à jour.
     * Met à jour les cellules dépendantes de la cellule mise à jour.
     *
     * @param o   L'objet Observable (la cellule mise à jour).
     * @param arg Argument fourni avec la notification de mise à jour (la cellule mise à jour).
     */
    @Override
    public void update(Observable o, Object arg) {
        Cell cell = (Cell) arg;
        AbstractSyntaxTree cellAst = this.abstractSyntaxTrees[cell.getRow()][cell.getCol()];

        if (cellAst == null) {
            return;
        }

        HashSet<String> dependentCells = getDependentCells(cell);

        // Mettre à jour les cellules qui dépendent de la cellule qui a été mise à jour.
        System.out.println("Les cellules " + dependentCells + " ont besoin de " + cell.getCellReference());

        for (String cellReference : dependentCells) {
            int row = CellOperations.getRowFromCellReference(cellReference);
            int col = CellOperations.getColFromCellReference(cellReference);
            AbstractSyntaxTree ast = abstractSyntaxTrees[row][col];

            System.out.println("Row = " + row + ", col = " + col);

            try {
                // Évaluer la formule et mettre à jour l'affichage de la cellule
                HashSet<String> visited = new HashSet<>();
                double result = ast.evaluate(visited);
                cellsLabels[row][col].setText(String.valueOf(result));
            } catch (IncalculableFormulaException | IncorrectFormulaException exception) {
                System.err.println(exception.getMessage());
            }
        }
    }

    /**
     * Récupère un ensemble de références de cellules qui dépendent de la cellule spécifiée.
     *
     * @param cell La cellule dont les dépendants sont recherchés.
     * @return HashSet<String> contenant les références des cellules dépendantes.
     */
    private HashSet<String> getDependentCells(Cell cell) {
        HashSet<String> dependentCells = new HashSet<>();

        // Trouver les cellules qui ont besoin de la cellule qui a été mise à jour.
        for (AbstractSyntaxTree[] syntaxTree : this.abstractSyntaxTrees) {
            for (AbstractSyntaxTree abstractSyntaxTree : syntaxTree) {
                if (abstractSyntaxTree == null) {
                    continue;
                }
                HashSet<String> dependencies = abstractSyntaxTree.getDependentCells();

                if (dependencies.contains(cell.getCellReference())) {
                    dependentCells.add(abstractSyntaxTree.getOriginCellReference());
                }
            }
        }
        return dependentCells;
    }
}
