    public String getWeblogSearchFeedURLTemplate(Weblog weblog) {
        if(weblog == null) {
            return null;
        }
        
        StringBuilder url = new StringBuilder();
        
        url.append(getWeblogURL(weblog, null, true));
        url.append("feed/entries/atom");
        
        Map params = new HashMap();
        params.put("q", "{searchTerms}");
        params.put("page", "{startPage}");
        
        return url.toString() + URLUtilities.getQueryString(params);
    }
