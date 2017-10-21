    public boolean isBlacklisted(
         String str, List<String> moreStringRules, List<Pattern> moreRegexRules) {
        if (str == null || StringUtils.isEmpty(str)) {
            return false;
        }

        // First iterate over blacklist, doing indexOf.
        // Then iterate over blacklistRegex and test.
        // As soon as there is a hit in either case return true
        
        // test plain String.indexOf
        List<String> stringRules = blacklistStr;
        if (moreStringRules != null && moreStringRules.size() > 0) {
            stringRules = new ArrayList<String>();
            stringRules.addAll(moreStringRules);
            stringRules.addAll(blacklistStr);
        }
        if (testStringRules(str, stringRules)) {
            return true;
        }
        
        // test regex blacklisted
        List<Pattern> regexRules = blacklistRegex;
        if (moreRegexRules != null && moreRegexRules.size() > 0) {
            regexRules = new ArrayList<Pattern>();
            regexRules.addAll(moreRegexRules);
            regexRules.addAll(blacklistRegex);
        }
        return testRegExRules(str, regexRules);
    }      
