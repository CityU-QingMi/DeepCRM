    private static boolean testRegExRules(String str, List<Pattern> regexRules) {
        for (Pattern testPattern : regexRules) {
            // want to see what it is matching on, but only in debug mode
            if (mLogger.isDebugEnabled()) {
                Matcher matcher = testPattern.matcher(str);
                if (matcher.find()) {
                    mLogger.debug(matcher.group()
                            + " matched by " + testPattern.pattern());
                    return true;
                }
            } else {
                if (testPattern.matcher(str).find()) {
                    return true;
                }
            }
        }
        return false;
    }
