    private static boolean testComment(WeblogEntryComment c) {
        boolean ret = false;
        List<String> stringRules = new ArrayList<String>();
        List<Pattern> regexRules = new ArrayList<Pattern>();
        Weblog website = c.getWeblogEntry().getWebsite();
        Blacklist.populateSpamRules(
            website.getBlacklist(), stringRules, regexRules, 
            WebloggerRuntimeConfig.getProperty("spam.blacklist"));
        Blacklist blacklist = Blacklist.getBlacklist();
        if (   blacklist.isBlacklisted(c.getUrl(),     stringRules, regexRules)
            || blacklist.isBlacklisted(c.getEmail(),   stringRules, regexRules)
            || blacklist.isBlacklisted(c.getName(),    stringRules, regexRules)
            || blacklist.isBlacklisted(c.getContent(), stringRules, regexRules)) {
            ret = true;
        }
        return ret;
    }        
