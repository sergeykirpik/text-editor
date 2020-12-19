package editor.action;

import editor.TextEditor;

import javax.swing.*;

public abstract class TextEditorAction extends AbstractAction {

    protected TextEditor ui;

    protected TextEditorAction(TextEditor ui) {
        this.ui = ui;
    }

    protected void setText(String text) {
        putValue(Action.NAME, text);
    }

    protected void setIcon(Icon icon) {
        putValue(Action.LARGE_ICON_KEY, icon);
    }

}
