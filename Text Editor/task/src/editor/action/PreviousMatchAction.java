package editor.action;

import editor.TextEditor;
import editor.finder.Finder;

import java.awt.event.ActionEvent;

public class PreviousMatchAction extends TextEditorAction {

    public PreviousMatchAction(TextEditor ui) {
        super(ui);
        setText("Previous match");
        setIcon(ui.getIconByName("previous"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Thread t = new Thread(new Runnable() {
            final int pos = ui.textArea.getSelectionStart() - 1;
            final String what = ui.searchField.getText();
            final String where = ui.textArea.getText();

            @Override
            public void run() {
                Finder finder = ui.getFinder();
                boolean found = finder.searchFromPositionBackwards(pos, what, where);
                if (!found) {
                    found = finder.searchFromPositionBackwards(where.length() - 1, what, where);
                }
                if (found) {
                    ui.select(finder.start(), finder.end());
                }
            }
        });
        t.start();
    }
}
