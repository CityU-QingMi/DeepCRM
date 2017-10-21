    @Override
    public String getWeblogURL(Weblog weblog, String locale, boolean absolute) {
        
        if(weblog == null) {
            return null;
        }
        
        StringBuilder url = new StringBuilder();
        
        if(absolute) {
            url.append(WebloggerRuntimeConfig.getAbsoluteContextURL());
        } else {
            url.append(WebloggerRuntimeConfig.getRelativeContextURL());
        }
        
        url.append(PREVIEW_URL_SEGMENT).append(weblog.getHandle()).append("/");
        
        if(locale != null) {
            url.append(locale).append("/");
        }
        
        Map params = new HashMap();
        if(previewTheme != null) {
            params.put("theme", URLUtilities.encode(previewTheme));
        }
        
        return url.toString() + URLUtilities.getQueryString(params);
    }
