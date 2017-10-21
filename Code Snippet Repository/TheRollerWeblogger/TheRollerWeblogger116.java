    public String getWeblogTagsJsonURL(Weblog weblog,
                                                    boolean absolute,
                                                    int pageNum) {
        
        StringBuilder url = new StringBuilder();
        
        if (absolute) {
            url.append(WebloggerRuntimeConfig.getAbsoluteContextURL());
        } else {
            url.append(WebloggerRuntimeConfig.getRelativeContextURL());
        }
        
        // json tags service base
        url.append("/roller-services/tagdata/");
        
        // is this for a specific weblog or site-wide?
        if (weblog != null) {
            url.append("weblog/");
            url.append(weblog.getHandle());
            url.append("/");
        }
        
        if (pageNum > 0) {
            url.append("?page=").append(pageNum);
        }
        
        return url.toString();
    }
