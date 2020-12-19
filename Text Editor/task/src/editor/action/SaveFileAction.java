package editor.action;

import editor.TextEditor;

import java.awt.event.ActionEvent;
import java.io.*;

public class SaveFileAction extends TextEditorAction {

    public SaveFileAction(TextEditor ui) {
        super(ui);
        setText("Save");
        setIcon(ui.getIconByName("save"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        saveFile(ui.showSaveFileDialog());
    }

    private void saveFile(File file) {
        if (file == null) {
            return;
        }
        try (Writer writer = new BufferedWriter(new FileWriter(file))) {
            ui.textArea.write(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
