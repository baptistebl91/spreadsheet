import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

/**
 * FormulaBarListener est un ActionListener qui réagit aux actions effectuées sur la barre de formule.
 * Il traite la saisie de l'utilisateur dans la barre de formule et met à jour la cellule sélectionnée en conséquence.
 *
 * @version 1.0
 */

public class FormulaBarListener implements ActionListener {
    private final TabPage tabPage;

    /**
     * Constructeur de FormulaBarListener.
     *
     * @param tabPage La page d'onglet contenant les cellules et la barre de formule.
     */
    public FormulaBarListener(TabPage tabPage) {
        this.tabPage = tabPage;
    }

    /**
     * Gère l'action effectuée sur la barre de formule.
     * Lorsqu'une formule est entrée ou modifiée, cette méthode met à jour la cellule sélectionnée avec
     * la nouvelle formule, évalue cette formule, et met à jour l'affichage de la cellule selon le résultat ou les
     * erreurs générées.
     *
     * @param e L'événement d'action qui a déclenché cette méthode.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField formulaBar = (JTextField) e.getSource();
        String formula = formulaBar.getText();
        CellLabel selectedCellLabel = tabPage.selectedCellLabel;

        // Lorsque la fenêtre charge, aucune cellule n'est cliquée et
        // donc le texte rentré dans le JTextField ne doit pas être pris en compte.
        if (selectedCellLabel == null) {
            return;
        }

        if (formula.isEmpty()) {
            // Supprimer la formule de la cellule
            selectedCellLabel.getCell().setFormula("");
            // Supprimer le résultat de l'affichage
            selectedCellLabel.setText("");
            // Supprimer l'AST
            tabPage.abstractSyntaxTrees[selectedCellLabel.getCell().getRow()][selectedCellLabel.getCell().getCol()] = null;

            selectedCellLabel.setEmptyFormulaBackgroundColor();

        } else {
            String previousFormula = selectedCellLabel.getCell().getFormula();
            try {
                // Évaluer la formule et mettre à jour l'affichage de la cellule
                AbstractSyntaxTree ast = new AbstractSyntaxTree(selectedCellLabel.getCell().getCellReference(), formula, tabPage.abstractSyntaxTrees);
                HashSet<String> visited = new HashSet<>();
                double result = ast.evaluate(visited);
                selectedCellLabel.setText(String.valueOf(result));
                // Mise à jour de l'AST dans le tableau pour des références futures
                tabPage.abstractSyntaxTrees[selectedCellLabel.getCell().getRow()][selectedCellLabel.getCell().getCol()] = ast;
                // Mettre à jour la formule de la cellule
                selectedCellLabel.getCell().setFormula(formula);
                selectedCellLabel.setCalculableFormulaColor();
            } catch (IncalculableFormulaException exception) {
                System.err.println(exception.getMessage());
                selectedCellLabel.setIncalculableFormulaColor();
                // Remettre la formule précédente dans la cellule en cas d'erreur.
                selectedCellLabel.getCell().setFormula(previousFormula);
            } catch (IncorrectFormulaException exception) {
                System.err.println(exception.getMessage());
                selectedCellLabel.setIncorrectFormulaColor();
                // Remettre la formule précédente dans la cellule en cas d'erreur.
                selectedCellLabel.getCell().setFormula(previousFormula);
            }
        }
    }
}
