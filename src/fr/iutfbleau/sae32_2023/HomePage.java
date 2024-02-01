import javax.swing.*;
import java.awt.*;

/**
 * HomePage représente la fenêtre principale de l'application "Petit Tableur".
 * Elle fournit une interface utilisateur pour accéder aux différentes fonctionnalités de l'application,
 * comme le tableur lui-même et l'option de sortie.
 *
 * @version 1.0
 */
public class HomePage extends JFrame {
    public final CardLayout cardLayout;

    /**
     * Constructeur de HomePage.
     * Initialise la fenêtre avec un titre, une taille, une action par défaut de fermeture et la disposition.
     */
    public HomePage() {
        setTitle("Petit Tableur");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        setLayout(cardLayout);

        JPanel mainPanel = createMainPanel();
        add(mainPanel, "MainPanel");
        add(new TabPage(), "TabPage");
    }

    /**
     * Crée le panneau principal de la page d'accueil.
     * Ce panneau contient le titre de l'application et les boutons pour naviguer dans l'application.
     *
     * @return Le JPanel créé pour le panneau principal.
     */
    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        JLabel titleLabel = createTitleLabel();
        JPanel buttonsPanel = createButtonsPanel();

        mainPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(buttonsPanel);

        return mainPanel;
    }

    /**
     * Crée le label du titre pour la page d'accueil.
     * Définit le texte, la couleur, la police et l'alignement du titre.
     *
     * @return Le JLabel créé pour le titre.
     */
    private JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel("SAE32_2023 : Petit Tableur");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Courier", Font.PLAIN, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return titleLabel;
    }

    /**
     * Crée le panneau contenant les boutons pour la page d'accueil.
     * Ce panneau contient des boutons pour accéder au tableur et quitter l'application.
     *
     * @return Le JPanel créé pour les boutons.
     */
    private JPanel createButtonsPanel() {
        JButton btnTableur = createButton("Accéder au tableur");
        btnTableur.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnTableur.addActionListener(new SheetAccessButtonListener(this));

        JButton btnQuitter = createButton("Quitter");
        btnQuitter.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnQuitter.addActionListener(new ExitButtonListener());

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.add(btnTableur);
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonsPanel.add(btnQuitter);

        return buttonsPanel;
    }

    /**
     * Crée un bouton standard pour l'application.
     * Définit le texte, la couleur de fond, la couleur du texte et la police du bouton.
     *
     * @param text Le texte à afficher sur le bouton.
     * @return Le JButton créé.
     */
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
        button.setFont(new Font("Courier", Font.PLAIN, 12));
        return button;
    }
}
