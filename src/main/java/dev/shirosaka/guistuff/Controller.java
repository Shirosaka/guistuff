package dev.shirosaka.guistuff;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

@SuppressWarnings("FieldMayBeFinal")
public class Controller {
    private View view;
    private DialogController dController;

    // File picker filters
    private FileNameExtensionFilter pictureFilter;
    private FileNameExtensionFilter editorPaneFilter;

    // count stuff
    private int count = 0;

    public Controller(View view, DialogController dController) {
        this.view = view;
        this.dController = dController;

        pictureFilter = new FileNameExtensionFilter("Pictures (.png; .jpg)", "png", "jpg");
        editorPaneFilter = new FileNameExtensionFilter("Programming (.html; .css; .js)", "html", "css", "js");
    }

    public void init() {
        view.getFileChooser().setMultiSelectionEnabled(false);
        view.getFileChooser().setAcceptAllFileFilterUsed(false);

        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);

                int result = JOptionPane.showConfirmDialog(null, "Do you want to close this window?", "reeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION)
                    view.dispose();
            }
        });

        view.getButton().addActionListener(e -> log("Hello, World!", "Button"));
        view.getCheckBox().addActionListener(e -> log("New state: " + (view.getCheckBox().isSelected() ? "Selected" : "Unselected"), "CheckBox"));
        view.getRadioButton().addActionListener(e -> log("New state: " + (view.getRadioButton().isSelected() ? "Selected" : "Unselected"), "RadioButton"));
        view.getSlider().addChangeListener(e -> log("New value: " + view.getSlider().getValue(), "Slider"));
        view.getTextField().addActionListener(e -> log("New value: " + view.getTextField().getText(), "TextField"));
        view.getPasswordField().addActionListener(e -> log("New value: " + Arrays.toString(view.getPasswordField().getPassword()), "PasswordField"));

        view.getOpenEditorMenuItem().addActionListener(e -> {
            var fileChooser = view.getFileChooser();

            fileChooser.removeChoosableFileFilter(pictureFilter);
            fileChooser.removeChoosableFileFilter(editorPaneFilter);
            fileChooser.setFileFilter(editorPaneFilter);

            if (fileChooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
                var file = fileChooser.getSelectedFile();

                if (file == null) {
                    log("No file was chosen.", "FileChooser");
                    return;
                }

                log("Chosen file: " + file.getName(), "FileChooser");

                dController.showWithFile(file);
            }
        });

        view.getChooseImageButton().addActionListener(e -> {
            var fileChooser = view.getFileChooser();

            fileChooser.removeChoosableFileFilter(pictureFilter);
            fileChooser.removeChoosableFileFilter(editorPaneFilter);
            fileChooser.setFileFilter(pictureFilter);

            if (fileChooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
                var file = fileChooser.getSelectedFile();

                if (file == null) {
                    log("No file was chosen.", "FileChooser");
                    return;
                }

                log("Chosen file: " + file.getName(), "FileChooser");

                view.getImageLabel().setIcon(getImage(file.getAbsolutePath()));
            }
        });

        view.getComboBox().addItemListener(e -> {
            if (e.getItem() != view.getComboBox().getSelectedItem())
                return;

            log("New item: " + e.getItem(), "ComboBox");
        });

        view.getShowLabelButton().addActionListener(e -> {
            log("Showing label", "ShowLabelButton");
            view.getShowHideLabel().setVisible(true);
        });

        view.getHideLabelButton().addActionListener(e -> {
            log("Hiding label", "HideLabelButton");
            view.getShowHideLabel().setVisible(false);
        });

        view.getCountButton().addActionListener(e -> {
            count++;
            log("New count value: " + count, "Counter");
            view.getCountLabel().setText("Count: " + count);
        });

        view.getResetButton().addActionListener(e -> {
            count = 0;
            log("Count reset!", "Counter");
            view.getCountLabel().setText("Count: " + count);
        });
    }

    private void log(String message, String source) {
        var tf = view.getTextArea();
        tf.setText(tf.getText() + String.format("\n[%s] %s", source, message));
    }

    private ImageIcon getImage(String path) {
        var imgLabel = view.getImageLabel();
        var icon = new ImageIcon(path);
        var prevImg = icon.getImage();
        var newImg = prevImg.getScaledInstance(imgLabel.getWidth(), imgLabel.getHeight(), Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }
}
