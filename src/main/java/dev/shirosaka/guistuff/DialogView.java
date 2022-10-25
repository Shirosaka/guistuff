package dev.shirosaka.guistuff;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("FieldMayBeFinal")
public class DialogView extends JDialog {
    // menu
    private JMenuBar menuBar;
    // file menu
    private JMenu fileMenu;
    private JMenuItem fileOpenItem;
    private JMenuItem fileSaveItem;
    // edit menu
    private JMenu editMenu;
    private JMenuItem editCutItem;
    private JMenuItem editCopyItem;
    private JMenuItem editPasteItem;
    private JMenuItem editSelectAllItem;

    // editor
    private JEditorPane editorPane;
    private JScrollPane scrollPane;
    private JFileChooser fileChooser;

    public DialogView() {
        setTitle("shirosaka guistuff - i hate guis");
        setSize(1280, 800);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        // menu stuff
        fileOpenItem = new JMenuItem("Open");
        fileSaveItem = new JMenuItem("Save");
        fileMenu = new JMenu("File");
        fileMenu.add(fileOpenItem);
        fileMenu.add(fileSaveItem);
        editCutItem = new JMenuItem("Cut");
        editCopyItem = new JMenuItem("Copy");
        editPasteItem = new JMenuItem("Paste");
        editSelectAllItem = new JMenuItem("Select All");
        editMenu = new JMenu("Edit");
        editMenu.add(editCutItem);
        editMenu.add(editCopyItem);
        editMenu.add(editPasteItem);
        editMenu.add(editSelectAllItem);
        menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        setJMenuBar(menuBar);

        // file chooser
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Programming (.html; .css; .js)", "html", "css", "js"));

        // editor stuff
        editorPane = new JEditorPane();
        scrollPane = new JScrollPane(editorPane);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        add(scrollPane);
    }

    public JEditorPane getEditorPane() {
        return editorPane;
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    public JMenuItem getEditCutItem() {
        return editCutItem;
    }

    public JMenuItem getEditCopyItem() {
        return editCopyItem;
    }

    public JMenuItem getEditPasteItem() {
        return editPasteItem;
    }

    public JMenuItem getEditSelectAllItem() {
        return editSelectAllItem;
    }

    public JMenuItem getFileOpenItem() {
        return fileOpenItem;
    }

    public JMenuItem getFileSaveItem() {
        return fileSaveItem;
    }
}
