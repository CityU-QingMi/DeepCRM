    public String getPlanetGroupFeedURL(String planet, String group, String format) {
        
        StringBuilder url = new StringBuilder();
        String sep = "?";
        
        url.append(WebloggerRuntimeConfig.getAbsoluteContextURL());
        url.append("planetrss");

        if (group != null) {
            url.append(sep);
            url.append("group=").append(group);
        }
        
        return url.toString();
    }
