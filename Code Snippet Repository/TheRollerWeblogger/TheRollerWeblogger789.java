    public static boolean checkReferrer(Weblog website, String referrerURL) {
        if (WebloggerConfig.getBooleanProperty("site.blacklist.enable.referrers")) {
            List<String> stringRules = new ArrayList<String>();
            List<Pattern> regexRules = new ArrayList<Pattern>();
            Blacklist.populateSpamRules(
                website.getBlacklist(), stringRules, regexRules, null);
            if (WebloggerRuntimeConfig.getProperty("spam.blacklist") != null) {
                Blacklist.populateSpamRules(
                    WebloggerRuntimeConfig.getProperty("spam.blacklist"), stringRules, regexRules, null);
            }
            return Blacklist.matchesRulesOnly(referrerURL, stringRules, regexRules);
        }
        return false;
    }
