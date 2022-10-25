package dev.shirosaka.guistuff;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@SuppressWarnings("FieldMayBeFinal")
public class DialogController {
    private DialogView view;

    private File currentFile;
    private boolean fileChanged;

    public DialogController(DialogView view) {
        this.view = view;

        this.currentFile = null;
        this.fileChanged = false;
    }

    public void init() {
        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                saveChangesDialog();
                view.dispose();
            }
        });

        var editorPane = view.getEditorPane();

        editorPane.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("File changed!");
                fileChanged = true;
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        view.getEditCutItem().addActionListener(e -> {
            editorPane.cut();
        });

        view.getEditCopyItem().addActionListener(e -> {
            editorPane.copy();
        });

        view.getEditPasteItem().addActionListener(e -> {
            editorPane.paste();
        });

        view.getEditSelectAllItem().addActionListener(e -> {
            editorPane.selectAll();
        });

        view.getFileSaveItem().addActionListener(e -> {
            saveFile();
        });

        view.getFileOpenItem().addActionListener(e -> {
            var fileChooser = view.getFileChooser();
            fileChooser.showOpenDialog(view);
            openFile(fileChooser.getSelectedFile());
        });
    }

    public void showWithFile(File file) {
        openFile(file);
        view.setVisible(true);
    }

    private void openFile(File file) {
        if (file == null)
            return;

        System.out.println("Opening file " + file.getName());

        var editorPane = view.getEditorPane();
        var fileExt = getExtensionByStringHandling(file.getName());

        if (fileExt.isEmpty()) {
            return;
        }

        editorPane.setContentType("text/" + fileExt.get());

        try {
            fileChanged = false;
            editorPane.setText(Files.readString(Path.of(file.getPath())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        currentFile = file;
    }

    private void saveFile() {
        System.out.println("Saving file " + currentFile.getName());

        try {
            Files.writeString(Path.of(currentFile.toURI()), view.getEditorPane().getText());
            System.out.println("Changes written to " + currentFile.getPath());
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }

        fileChanged = false;
    }

    private void saveChangesDialog() {
        if (fileChanged) {
            int result = JOptionPane.showConfirmDialog(null, "You have outstanding changes. Do you want to save them?", "reeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", JOptionPane.YES_NO_CANCEL_OPTION);

            if (result == JOptionPane.CANCEL_OPTION)
                return;

            if (result == JOptionPane.YES_OPTION) {
                saveFile();
            } else {
                System.out.println("Continuing without saving changes.");
            }
        }
    }

    private Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }
}
