    public String getWeblogConfigURL(String weblogHandle,
                                                  boolean absolute) {

        StringBuilder url = new StringBuilder();
        
        if(absolute) {
            url.append(WebloggerRuntimeConfig.getAbsoluteContextURL());
        } else {
            url.append(WebloggerRuntimeConfig.getRelativeContextURL());
        }
        
        url.append("/roller-ui/authoring/weblogConfig.rol");
        
        Map params = new HashMap();
        params.put("weblog", weblogHandle);
        
        return url.toString() + URLUtilities.getQueryString(params);
    }
