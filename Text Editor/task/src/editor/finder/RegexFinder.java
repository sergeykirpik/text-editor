package editor.finder;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexFinder implements Finder {

    private int currentMatch;
    private final List<MatchResult> matches = new ArrayList<>();

    public boolean startSearch(String what, String where) {
        currentMatch = 0;
        matches.clear();
        Pattern searchPattern = Pattern.compile(what);
        Matcher matcher = searchPattern.matcher(where);

        while (matcher.find()) {
            matches.add(matcher.toMatchResult());
        }

        return !matches.isEmpty();
    }

    public int start() {
        return matches.get(currentMatch).start();
    }

    public int end() {
        return matches.get(currentMatch).end();
    }

    public boolean next() {
        if (matches.isEmpty()) {
            return false;
        }
        currentMatch++;
        if (currentMatch > matches.size() - 1) {
            currentMatch = 0;
        }
        return true;
    }

    public boolean prev() {
        if (matches.isEmpty()) {
            return false;
        }
        currentMatch--;
        if (currentMatch < 0) {
            currentMatch = matches.size() - 1;
        }
        return true;
    }
}
