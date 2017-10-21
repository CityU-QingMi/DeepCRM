    private String createURL(String base, Map<String, String> params) {
        String qString = URLUtilities.getQueryString(params);
        
        if(base.indexOf('?') != -1) {
            // if base url already has params them just append our query string
            return base + "&" + qString.substring(1);
        } else {
            return base + qString;
        }
    }
