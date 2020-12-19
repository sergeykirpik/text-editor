package editor.action;

import editor.TextEditor;

import java.awt.event.ActionEvent;
import java.io.*;

public class OpenFileAction extends TextEditorAction {

    public OpenFileAction(TextEditor ui) {
        super(ui);
        setText("Open");
        setIcon(ui.getIconByName("open"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        openFile(ui.showOpenFileDialog());
    }

    private void openFile(File file) {
        if (file == null) {
            return;
        }
        ui.textArea.setText(null);
        if (!file.isFile()) {
            return;
        }
        try (Reader reader = new BufferedReader(new FileReader(file))) {
            ui.textArea.read(reader, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
