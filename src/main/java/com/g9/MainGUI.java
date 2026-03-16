package com.g9;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.g9.model.JourSemaine;
import com.g9.model.Planning;
import com.g9.service.CourseService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainGUI extends JFrame {
    private CourseService courseService;
    private JTable table;
    private DefaultTableModel tableModel;
    private boolean isDarkMode = true;

    private JTextField txtCours, txtProf, txtSalle, txtDebut, txtFin;
    private JComboBox<JourSemaine> cbJour;
    private JPanel sidebar;

    public MainGUI() {
        courseService = new CourseService();
        setTitle("G9 Planning System Pro");
        setSize(1200, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // --- 1. HEADER ---
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(63, 81, 181)); 
        header.setPreferredSize(new Dimension(0, 70));
        header.setBorder(new EmptyBorder(0, 20, 0, 20));

        JLabel lblLogo = new JLabel("📅 G9 | DASHBOARD ÉTUDIANT");
        lblLogo.setForeground(Color.WHITE);
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.add(lblLogo, BorderLayout.WEST);

        JButton btnTheme = new JButton("🌓 Changer de mode");
        btnTheme.setFocusPainted(false);
        btnTheme.addActionListener(e -> toggleTheme());
        header.add(btnTheme, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // --- 2. LAYOUT CENTRAL ---
        JPanel mainContainer = new JPanel(new BorderLayout(20, 0));
        mainContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(mainContainer, BorderLayout.CENTER);

        // --- 3. SIDEBAR (Formulaire) ---
        sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(280, 0)); // Un peu plus étroit

        JLabel lblAdd = new JLabel("AJOUTER UN COURS");
        lblAdd.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblAdd.setForeground(new Color(63, 81, 181));
        sidebar.add(lblAdd);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));

        txtCours = createField("Nom du cours...", "📘 ");
        txtProf = createField("Professeur...", "👨‍🏫 ");
        txtSalle = createField("Salle...", "📍 ");
        
        // CORRECTION DU SELECTEUR DE JOUR
        JLabel lblJour = new JLabel("Day of week:");
        lblJour.setFont(new Font("Segoe UI", Font.BOLD, 11));
        sidebar.add(lblJour);
        cbJour = new JComboBox<>(JourSemaine.values());
        cbJour.putClientProperty("JComponent.roundRect", true);
        cbJour.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35)); // FIX: Hauteur limitée
        cbJour.setAlignmentX(Component.LEFT_ALIGNMENT);
        sidebar.add(cbJour);
        sidebar.add(Box.createRigidArea(new Dimension(0, 15)));

        txtDebut = createField("Début (HH:mm)", "🕒 ");
        txtFin = createField("Fin (HH:mm)", "🏁 ");

        JButton btnSave = new JButton("ENREGISTRER");
        btnSave.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnSave.setBackground(new Color(46, 204, 113));
        btnSave.setForeground(Color.WHITE);
        btnSave.putClientProperty("JButton.buttonType", "roundRect");
        btnSave.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSave.addActionListener(e -> actionSave());

        sidebar.add(btnSave);
        mainContainer.add(sidebar, BorderLayout.WEST);

        // --- 4. TABLEAU ET BOUTON SUPPRIMER ---
        JPanel tablePanel = new JPanel(new BorderLayout(0, 15));
        tablePanel.setOpaque(false);

        String[] columns = {"ID", "COURS", "PROFESSEUR", "SALLE", "JOUR", "HORAIRES"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setRowHeight(40);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.putClientProperty("JComponent.roundRect", true);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // AJOUT DU BOUTON SUPPRIMER EN BAS DU TABLEAU
        JButton btnDelete = new JButton("🗑️ Supprimer le cours sélectionné");
        btnDelete.setBackground(new Color(231, 76, 60)); // Rouge
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnDelete.putClientProperty("JButton.buttonType", "roundRect");
        btnDelete.setPreferredSize(new Dimension(0, 40));
        btnDelete.addActionListener(e -> actionDelete());
        
        tablePanel.add(btnDelete, BorderLayout.SOUTH);
        mainContainer.add(tablePanel, BorderLayout.CENTER);

        actualiserTableau();
    }

    private JTextField createField(String placeholder, String icon) {
        JLabel l = new JLabel(icon + placeholder);
        l.setFont(new Font("Segoe UI", Font.BOLD, 11));
        sidebar.add(l);
        sidebar.add(Box.createRigidArea(new Dimension(0, 5)));
        
        JTextField tf = new JTextField();
        tf.putClientProperty("JTextField.placeholderText", placeholder);
        tf.putClientProperty("JComponent.roundRect", true);
        tf.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        sidebar.add(tf);
        sidebar.add(Box.createRigidArea(new Dimension(0, 15)));
        return tf;
    }

    private void toggleTheme() {
        FlatAnimatedLafChange.showSnapshot();
        if (isDarkMode) {
            FlatLightLaf.setup();
            isDarkMode = false;
        } else {
            FlatDarkLaf.setup();
            isDarkMode = true;
        }
        SwingUtilities.updateComponentTreeUI(this);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    private void actionSave() {
        try {
            Planning p = new Planning(txtCours.getText(), txtProf.getText(), txtSalle.getText(), 
                                     (JourSemaine)cbJour.getSelectedItem(), txtDebut.getText(), txtFin.getText());
            courseService.addCourse(p);
            actualiserTableau();
            JOptionPane.showMessageDialog(this, "✅ Cours enregistré !");
            txtCours.setText(""); txtProf.setText(""); txtSalle.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "❌ Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actionDelete() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int courseId = (int) tableModel.getValueAt(selectedRow, 0);
            if (courseService.deleteCourse(courseId)) {
                actualiserTableau();
                JOptionPane.showMessageDialog(this, "✅ Cours supprimé !");
            } else {
                JOptionPane.showMessageDialog(this, "❌ Impossible de supprimer le cours.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un cours à supprimer.", "Avertissement", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void actualiserTableau() {
        tableModel.setRowCount(0);
        courseService.getAllCourses().forEach(p -> {
            tableModel.addRow(new Object[]{
                p.getId(), p.getNomCours(), p.getNomProf(), p.getNomSalle(), 
                p.getJourSemaine(), p.getHeureDebut() + " - " + p.getHeureFin()
            });
        });
    }

    // --- LA MÉTHODE MAIN EST ICI ---
    public static void main(String[] args) {
        // Setup initial
        FlatDarkLaf.setup();
        
        // Customisation globale avant de lancer
        UIManager.put("Button.arc", 15);
        UIManager.put("Component.arc", 15);

        SwingUtilities.invokeLater(() -> {
            MainGUI app = new MainGUI();
            app.setVisible(true);
        });
    }
}