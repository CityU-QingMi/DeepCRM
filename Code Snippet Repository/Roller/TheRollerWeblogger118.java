    @Override
    public String getWeblogEntryURL(Weblog weblog,
                                    String locale,
                                    String previewAnchor,
                                    boolean absolute) {
        
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
        if(previewAnchor != null) {
            params.put("previewEntry", URLUtilities.encode(previewAnchor));
        }
        
        return url.toString() + URLUtilities.getQueryString(params);
    }
