    private boolean isWeblog(String potentialHandle) {
        
        log.debug("checking weblog handle "+potentialHandle);
        
        boolean isWeblog = false;
        
        try {
            Weblog weblog = WebloggerFactory.getWeblogger().getWeblogManager()
                    .getWeblogByHandle(potentialHandle);
            
            if(weblog != null) {
                isWeblog = true;
            }
        } catch(Exception ex) {
            // doesn't really matter to us why it's not a valid website
        }
        
        return isWeblog;
    }
