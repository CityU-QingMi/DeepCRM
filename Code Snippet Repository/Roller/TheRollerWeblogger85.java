    public String getLogoutURL(boolean absolute) {

        StringBuilder url = new StringBuilder();
        
        if(absolute) {
            url.append(WebloggerRuntimeConfig.getAbsoluteContextURL());
        } else {
            url.append(WebloggerRuntimeConfig.getRelativeContextURL());
        }
        
        url.append("/roller-ui/logout.rol");
        
        return url.toString();
    }
