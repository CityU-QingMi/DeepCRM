    public static String getAbsoluteContextURL() {
        
        // db prop takes priority if it exists
        String absURL = getProperty("site.absoluteurl");
        if(absURL != null && absURL.trim().length() > 0) {
            return absURL;
        }
        
        return absoluteContextURL;
    }
