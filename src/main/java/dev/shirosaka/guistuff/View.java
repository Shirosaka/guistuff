package dev.shirosaka.guistuff;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("FieldMayBeFinal")
public class View extends JFrame {
    // counting
    private JLabel countLabel;
    private JButton countButton;
    private JButton resetButton;

    // swing controls
    private JLabel label;
    private JButton button;
    private JComboBox<String> comboBox;
    private JCheckBox checkBox;
    private JRadioButton radioButton;
    private JSlider slider;
    private JTextField textField;
    private JPasswordField passwordField;

    // show/hide label buttons
    private JButton showLabelButton;
    private JButton hideLabelButton;
    private JLabel showHideLabel;

    // Containers
    private JPanel redPanel;
    private JPanel greenPanel;
    private JPanel bluePanel;
    private JPanel swingControlsPanel;
    private JPanel borderLayoutPanel;
    private JPanel gridLayoutPanel;
    private JPanel flowLayoutPanel;
    private JPanel picturePanel;
    private JPanel controlContainer;
    private JTabbedPane tabbedPane;

    // menu bar
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem openEditorMenuItem;

    // image
    private JLabel imageLabel;
    private JLabel chooseImageLabel;
    private JButton chooseImageButton;

    // advanced components
    private JFileChooser fileChooser;
    private JTextArea textArea;

    // for the combobox
    private String[] p5rThieves = {"Amamiya Ren", "Morgana", "Sakamoto Ryuji", "Takamaki Ann", "Kitagawa Yusuke", "Nijima Makoto", "Sakura Futaba", "Okumura Haru", "Yoshizawa Kasumi"};

    public View() {
        // set default window info
        setTitle("shirosaka guistuff - i hate guis");
        setSize(1280, 800);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        // center window location
        setLocationRelativeTo(null);

        // ---- UI ----
        // menu bar
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        openEditorMenuItem = new JMenuItem("Open Editor");
        fileMenu.add(openEditorMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // some panels
        redPanel = new JPanel(null);
        redPanel.setBackground(Color.RED);
        greenPanel = new JPanel(null);
        greenPanel.setBackground(Color.GREEN);
        bluePanel = new JPanel(null);
        bluePanel.setBackground(Color.BLUE);
        swingControlsPanel = new JPanel(new GridLayout(0, 2));

        // controls
        controlContainer = new JPanel(new GridLayout(0, 1));
        label = new JLabel("This is a label!");
        controlContainer.add(label);
        button = new JButton("This is a button!");
        controlContainer.add(button);
        comboBox = new JComboBox<>(p5rThieves);
        controlContainer.add(new JLabel("This is a combo box filled with all Persona 5 Thieves!"));
        controlContainer.add(comboBox);
        checkBox = new JCheckBox("This is a check box!");
        controlContainer.add(checkBox);
        radioButton = new JRadioButton("This is a radio button!");
        controlContainer.add(radioButton);
        slider = new JSlider(1, 100);
        controlContainer.add(new JLabel("This is a slider!"));
        controlContainer.add(slider);
        textField = new JTextField("This is a text field!");
        controlContainer.add(textField);
        passwordField = new JPasswordField("This is a password field!");
        controlContainer.add(passwordField);

        // showing / hiding components
        var showHideButtonContainer = new JPanel();
        showLabelButton = new JButton("Show label");
        hideLabelButton = new JButton("Hide label");
        showHideButtonContainer.add(showLabelButton);
        showHideButtonContainer.add(hideLabelButton);
        controlContainer.add(showHideButtonContainer);
        showHideLabel = new JLabel("This label magically appeared!");
        controlContainer.add(showHideLabel);

        // changing component
        var countContainer = new JPanel();
        countButton = new JButton("Increment");
        resetButton = new JButton("Reset");
        countLabel = new JLabel("Click the button to count!");
        countContainer.add(countButton);
        countContainer.add(resetButton);
        countContainer.add(countLabel);
        controlContainer.add(countContainer);

        swingControlsPanel.add(controlContainer);

        // text area (real-time logs)
        textArea = new JTextArea("To type text, please open the Editor through a valid .html, .css, or .js file by clicking on File -> Open Editor in the menu.");
        textArea.setEnabled(false);
        swingControlsPanel.add(new JScrollPane(textArea));

        // file chooser
        fileChooser = new JFileChooser();

        // border layout
        borderLayoutPanel = new JPanel(new BorderLayout());
        borderLayoutPanel.add(new JButton("PAGE_START"), BorderLayout.PAGE_START);
        borderLayoutPanel.add(new JButton("LINE_START"), BorderLayout.LINE_START);
        borderLayoutPanel.add(new JButton("CENTER"), BorderLayout.CENTER);
        borderLayoutPanel.add(new JButton("LINE_END"), BorderLayout.LINE_END);
        borderLayoutPanel.add(new JButton("PAGE_END"), BorderLayout.PAGE_END);

        // grid layout
        gridLayoutPanel = new JPanel(new GridLayout(3, 2));
        gridLayoutPanel.add(new JButton("Row 1 Column 1"));
        gridLayoutPanel.add(new JButton("Row 1 Column 2"));
        gridLayoutPanel.add(new JButton("Row 2 Column 1"));
        gridLayoutPanel.add(new JButton("Row 1 Column 2"));
        gridLayoutPanel.add(new JButton("Row 3 Column 1"));
        gridLayoutPanel.add(new JButton("Row 3 Column 2"));

        // flow layout
        flowLayoutPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        flowLayoutPanel.add(new JButton("Label 1"));
        flowLayoutPanel.add(new JButton("Label 2"));
        flowLayoutPanel.add(new JButton("Label 3"));
        flowLayoutPanel.add(new JButton("Label 4"));
        flowLayoutPanel.add(new JButton("Label 5"));
        flowLayoutPanel.add(new JButton("Label 6"));

        // image tab
        var imageChooserContainer = new JPanel(new GridLayout(2, 1));
        chooseImageLabel = new JLabel("Choose an image from your file system.");
        chooseImageButton = new JButton("Choose");
        imageChooserContainer.add(chooseImageLabel);
        imageChooserContainer.add(chooseImageButton);
        imageLabel = new JLabel();
        picturePanel = new JPanel(new BorderLayout());
        picturePanel.add(imageChooserContainer, BorderLayout.PAGE_START);
        picturePanel.add(imageLabel, BorderLayout.CENTER);

        // tabbed pane
        tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.addTab("Red panel", redPanel);
        tabbedPane.addTab("Green panel", greenPanel);
        tabbedPane.addTab("Blue panel", bluePanel);
        tabbedPane.addTab("Controls", swingControlsPanel);
        tabbedPane.addTab("Border layout", borderLayoutPanel);
        tabbedPane.addTab("Grid layout", gridLayoutPanel);
        tabbedPane.addTab("Flow layout", flowLayoutPanel);
        tabbedPane.addTab("Picture", picturePanel);
        tabbedPane.setSelectedIndex(3);

        add(tabbedPane);
    }

    public JButton getButton() {
        return button;
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }

    public JCheckBox getCheckBox() {
        return checkBox;
    }

    public JRadioButton getRadioButton() {
        return radioButton;
    }

    public JSlider getSlider() {
        return slider;
    }

    public JTextField getTextField() {
        return textField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JLabel getShowHideLabel() {
        return showHideLabel;
    }

    public JButton getShowLabelButton() {
        return showLabelButton;
    }

    public JButton getHideLabelButton() {
        return hideLabelButton;
    }

    public JLabel getCountLabel() {
        return countLabel;
    }

    public JButton getCountButton() {
        return countButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public JButton getChooseImageButton() {
        return chooseImageButton;
    }

    public JLabel getImageLabel() {
        return imageLabel;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JMenuItem getOpenEditorMenuItem() {
        return openEditorMenuItem;
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }
}
