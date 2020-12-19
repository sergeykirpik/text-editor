package editor.action;

import editor.TextEditor;
import editor.finder.Finder;

import java.awt.event.ActionEvent;

public class StartSearchAction extends TextEditorAction {

    public StartSearchAction(TextEditor ui) {
        super(ui);
        setText("Start search");
        setIcon(ui.getIconByName("search"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Thread t = new Thread(new Runnable() {
            final String what = ui.searchField.getText();
            final String where = ui.textArea.getText();

            @Override
            public void run() {
                Finder finder = ui.getFinder();
                if (finder.searchFromPosition(0, what, where)) {
                    ui.select(finder.start(), finder.end());
                }
            }
        });
        t.start();
    }
}
