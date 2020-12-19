package editor.action;

import editor.TextEditor;
import editor.finder.Finder;

import java.awt.event.ActionEvent;

public class NextMatchAction extends TextEditorAction {

    public NextMatchAction(TextEditor ui) {
        super(ui);
        setText("Next match");
        setIcon(ui.getIconByName("next"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Thread t = new Thread(new Runnable() {
            final int pos = ui.textArea.getSelectionEnd();
            final String what = ui.searchField.getText();
            final String where = ui.textArea.getText();

            @Override
            public void run() {
                Finder finder = ui.getFinder();
                boolean found = finder.searchFromPosition(pos, what, where);
                if (!found) {
                    found = finder.searchFromPosition(0, what, where);
                }
                if (found) {
                    ui.select(finder.start(), finder.end());
                }
            }
        });
        t.start();
    }
}
