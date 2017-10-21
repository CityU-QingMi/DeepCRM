    public Weblog getWeblog() {
        
        if(weblog == null && weblogHandle != null) {
            try {
                weblog = WebloggerFactory.getWeblogger().getWeblogManager()
                        .getWeblogByHandle(weblogHandle, Boolean.TRUE);
            } catch (WebloggerException ex) {
                log.error("Error looking up weblog "+weblogHandle, ex);
            }
        }
        
        return weblog;
    }
