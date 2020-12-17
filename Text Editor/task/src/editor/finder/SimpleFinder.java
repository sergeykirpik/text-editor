package editor.finder;

public class SimpleFinder implements Finder {

    private int fromIndex;
    private String what;
    private String where;

    public boolean startSearch(String what, String where) {
        this.what = what;
        this.where = where;
        fromIndex = where.indexOf(what);
        return fromIndex != -1;
    }

    public int start() {
        return fromIndex;
    }

    public int end() {
        return fromIndex + what.length();
    }

    public boolean next() {
        fromIndex += what.length();
        if (fromIndex >= where.length()) {
            fromIndex = 0;
        }
        fromIndex = where.indexOf(what, fromIndex);
        return fromIndex != -1;
    }

    public boolean prev() {
        fromIndex -= what.length();
        if (fromIndex < 0) {
            fromIndex = where.length();
        }
        fromIndex = where.lastIndexOf(what, fromIndex);
        return fromIndex != -1;
    }
}
