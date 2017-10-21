    private Integer historySearch(String findRegex) throws BadSpecial {
        if (history == null)
            throw new BadSpecial(SqltoolRB.history_unavailable.getString());
        Pattern pattern = null;
        try {
            pattern = Pattern.compile("(?ims)" + findRegex);
        } catch (PatternSyntaxException pse) {
            throw new BadSpecial(SqltoolRB.regex_malformat.getString(pse));
        }
        // Make matching more liberal.  Users can customize search behavior
        // by using "(?-OPTIONS)" or (?OPTIONS) in their regexes.
        for (int index = history.size() - 1; index >= 0; index--)
            if (pattern.matcher((history.get(index)).val).find())
                return Integer.valueOf(index + oldestHist);
        return null;
    }
