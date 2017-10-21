    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try{
            OAuthMessage requestMessage = OAuthServlet.getMessage(request, null);
            
            OAuthManager omgr = WebloggerFactory.getWeblogger().getOAuthManager();
            OAuthAccessor accessor = omgr.getAccessor(requestMessage);
            omgr.getValidator().validateMessage(requestMessage, accessor);
            
            // make sure token is authorized
            if (!Boolean.TRUE.equals(accessor.getProperty("authorized"))) {
                 OAuthProblemException problem = new OAuthProblemException("permission_denied");
                throw problem;
            }
            // generate access token and secret
            if (accessor.accessToken == null) {
                omgr.generateAccessToken(accessor);
                WebloggerFactory.getWeblogger().flush();
            }

            response.setContentType("text/plain");
            OutputStream out = response.getOutputStream();
            OAuth.formEncode(OAuth.newList(
                "oauth_token", accessor.accessToken,
                "oauth_token_secret", accessor.tokenSecret), out);
            out.close();
            
        } catch (Exception e){
            handleException(e, request, response, true);
        }
    }
