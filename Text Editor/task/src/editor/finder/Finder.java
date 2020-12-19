package editor.finder;

public interface Finder {
    boolean searchFromPosition(int pos, String what, String where);
    boolean searchFromPositionBackwards(int pos, String what, String where);
    int start();
    int end();
}
