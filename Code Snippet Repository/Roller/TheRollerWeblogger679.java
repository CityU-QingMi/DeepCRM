    @SkipValidation
    public String execute() {
        boolean flush = false;
        
        try {
            User ud = getAuthenticatedUser();
            OAuthManager omgr = WebloggerFactory.getWeblogger().getOAuthManager();
            userConsumer = omgr.getConsumerByUsername(ud.getUserName());
            if (userConsumer == null) {
                String consumerKey = DigestUtils.md5Hex(ud.getUserName());
                userConsumer = omgr.addConsumer(ud.getUserName(), consumerKey);
                flush = true;
            }

            if (isUserIsAdmin()) {
                siteWideConsumer = omgr.getConsumer();
                if (siteWideConsumer == null) {
                    String consumerKey = DigestUtils.md5Hex(
                        WebloggerRuntimeConfig.getAbsoluteContextURL());
                    siteWideConsumer = omgr.addConsumer(consumerKey);
                    flush = true;
                }
            }
            
            if (flush) {
                WebloggerFactory.getWeblogger().flush();
            }

        } catch (Exception ex) {
            log.error("ERROR creating or retrieving your OAuth information", ex);
        }

        return SUCCESS;
    }
