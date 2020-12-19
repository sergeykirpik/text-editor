package editor.finder;

public class SimpleFinder implements Finder {

    private int start;
    private int end;

    @Override
    public int start() {
        return start;
    }

    @Override
    public int end() {
        return end;
    }

    @Override
    public boolean searchFromPosition(int pos, String what, String where) {
        if (what == null || where == null) {
            return false;
        }
        start = where.indexOf(what, pos);
        end = start + what.length();
        return start != -1;
    }

    @Override
    public boolean searchFromPositionBackwards(int pos, String what, String where) {
        if (what == null || where == null) {
            return false;
        }
        start = where.lastIndexOf(what, pos);
        end = start + what.length();
        return start != -1;
    }
}
