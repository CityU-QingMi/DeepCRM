    private String authenticationOAUTH(
            HttpServletRequest request, HttpServletResponse response) {
        try {
            OAuthManager omgr = WebloggerFactory.getWeblogger().getOAuthManager();
            OAuthMessage requestMessage = OAuthServlet.getMessage(request, null);
            OAuthAccessor accessor = omgr.getAccessor(requestMessage);
            omgr.getValidator().validateMessage(requestMessage, accessor);
            return (String)accessor.consumer.getProperty("userId");

        } catch (Exception ex) {
            log.debug("ERROR authenticating user", ex);
            String realm = (request.isSecure())?"https://":"http://";
            realm += request.getLocalName();
            try {
                OAuthServlet.handleException(response, ex, realm, true);
            } catch (Exception ioe) {
                log.debug("ERROR writing error response", ioe);
            }
        }
        return null;
    }
