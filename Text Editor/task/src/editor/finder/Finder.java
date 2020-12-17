package editor.finder;

public interface Finder {
    boolean startSearch(String what, String where);
    int start();
    int end();
    boolean next();
    boolean prev();
}
