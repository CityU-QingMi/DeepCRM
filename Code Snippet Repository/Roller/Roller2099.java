    private boolean isLocale(String potentialLocale) {
        
        boolean isLocale = false;
        
        // we only support 2 or 5 character locale strings, so check that first
        if(potentialLocale != null && 
                (potentialLocale.length() == 2 || potentialLocale.length() == 5)) {
            
            // now make sure that the format is proper ... e.g. "en_US"
            // we are not going to be picky about capitalization
            String[] langCountry = potentialLocale.split("_");
            if(langCountry.length == 1 && 
                    langCountry[0] != null && langCountry[0].length() == 2) {
                isLocale = true;
                
            } else if(langCountry.length == 2 && 
                    langCountry[0] != null && langCountry[0].length() == 2 && 
                    langCountry[1] != null && langCountry[1].length() == 2) {
                
                isLocale = true;
            }
        }
        
        return isLocale;
    }
