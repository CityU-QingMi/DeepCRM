    public Locale getLocaleInstance() {
        
        if(localeInstance == null && locale != null) {
            String[] langCountry = locale.split("_");
            if(langCountry.length == 1) {
                localeInstance = new Locale(langCountry[0]);
            } else if(langCountry.length == 2) {
                localeInstance = new Locale(langCountry[0], langCountry[1]);
            }
        } else if(localeInstance == null) {
            localeInstance = getWeblog().getLocaleInstance();
        }
        
        return localeInstance;
    }
