    private void myValidate() {
        
        // make sure user didn't enter an invalid entry display count
        int maxEntries = WebloggerRuntimeConfig.getIntProperty("site.pages.maxEntries");
        if(getBean().getEntryDisplayCount() > maxEntries) {
            addError("websiteSettings.error.entryDisplayCount");
        }
        
        // check blacklist
        List regexRules = new ArrayList();
        List stringRules = new ArrayList();
        try {
            // just for testing/counting, this does not persist rules in any way
            Blacklist.populateSpamRules(getBean().getBlacklist(), stringRules, regexRules, null);
            addMessage("websiteSettings.acceptedBlacklist",
                    Arrays.asList(new String[] {""+stringRules.size(), ""+regexRules.size()}));
        } catch (Exception e) {
            addError("websiteSettings.error.processingBlacklist", e.getMessage());
        }
    }
