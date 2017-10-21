    public static void populateSpamRules(
        String blacklist, List<String> stringRules, List<Pattern> regexRules, String addendum) {
        String weblogWords = blacklist;
        weblogWords = (weblogWords == null) ? "" : weblogWords;
        String siteWords = (addendum != null) ? addendum : "";
        StringTokenizer toker = new StringTokenizer(siteWords + "\n" + weblogWords, "\n");
        while (toker.hasMoreTokens()) {
            String token = toker.nextToken().trim();
            if (token.startsWith("#")) {
                continue;
            }
            if (token.startsWith("(")) {
                regexRules.add(Pattern.compile(token));
            } else {
                stringRules.add(token);
            }
        }        
    }
