package editor.finder;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexFinder implements Finder {

   private MatchResult matchResult;

    @Override
    public boolean searchFromPosition(int pos, String what, String where) {
        if (what == null || where == null) {
            return false;
        }
        Pattern searchPattern = Pattern.compile(what);
        Matcher matcher = searchPattern.matcher(where);

        boolean found = matcher.find(pos);
        if (found) {
            matchResult = matcher.toMatchResult();
        }

        return found;
    }

    @Override
    public boolean searchFromPositionBackwards(int from, String what, String where) {
        if (what == null || where == null) {
            return false;
        }
        Pattern searchPattern = Pattern.compile(what);
        Matcher matcher = searchPattern.matcher(where.substring(0, from + 1));
        for (int pos = from - what.length(); pos >= 0; pos -= what.length()) {
            if (matcher.find(pos)) {
                matchResult = matcher.toMatchResult();
                return true;
            }
        }
        return false;
    }

    @Override
    public int start() {
        return matchResult.start();
    }

    @Override
    public int end() {
        return matchResult.end();
    }
}
