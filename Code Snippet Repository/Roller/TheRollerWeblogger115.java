    public String getWeblogResourceURL(Weblog weblog,
                                                    String filePath,
                                                    boolean absolute) {
        
        if(weblog == null || StringUtils.isEmpty(filePath)) {
            return null;
        }
        
        StringBuilder url = new StringBuilder();
        
        url.append(getWeblogURL(weblog, null, absolute));
        url.append("resource/");
        
        if(filePath.startsWith("/")) {
            url.append(URLUtilities.encodePath(filePath.substring(1)));
        } else {
            url.append(URLUtilities.encodePath(filePath));
        }
        
        return url.toString();
    }
