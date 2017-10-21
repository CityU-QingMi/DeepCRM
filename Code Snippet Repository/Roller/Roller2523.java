    public String getActionURL(String action,
                                            String namespace,
                                            String weblogHandle,
                                            Map<String, String> parameters,
                                            boolean absolute) {

        StringBuilder url = new StringBuilder();
        
        if(absolute) {
            url.append(WebloggerRuntimeConfig.getAbsoluteContextURL());
        } else {
            url.append(WebloggerRuntimeConfig.getRelativeContextURL());
        }
        
        url.append(namespace);
        url.append("/").append(action).append(".rol");
        
        // put weblog handle parameter, if necessary
        Map<String, String> params = new HashMap<String, String>();
        if(weblogHandle != null) {
            params.put("weblog", weblogHandle);
        }
        
        // add custom parameters if they exist
        if(parameters != null) {
            params.putAll(parameters);
        }
        
        if(!params.isEmpty()) {
            return url.toString() + URLUtilities.getQueryString(params);
        } else {
            return url.toString();
        }
    }
