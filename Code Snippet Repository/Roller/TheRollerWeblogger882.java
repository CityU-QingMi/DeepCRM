    public RollerAtomHandler(HttpServletRequest request, HttpServletResponse response) {
        roller = WebloggerFactory.getWeblogger();

        String userName;
        if ("oauth".equals(WebloggerRuntimeConfig.getProperty("webservices.atomPubAuth"))) {
            userName = authenticationOAUTH(request, response);

        } else if ("wsse".equals(WebloggerRuntimeConfig.getProperty("webservices.atomPubAuth"))) {
            userName = authenticateWSSE(request);

        } else {
            // default to basic
            userName = authenticateBASIC(request);
        }

        if (userName != null) {
            try {
                this.user = roller.getUserManager().getUserByUserName(userName);
            } catch (Exception neverHappen) {
                log.debug("Getting user", neverHappen);
            } 
        }
        
        atomURL = WebloggerFactory.getWeblogger().getUrlStrategy().getAtomProtocolURL(true);
    }
