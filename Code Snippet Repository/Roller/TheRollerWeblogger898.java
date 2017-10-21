    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        try {
            OAuthMessage requestMessage = OAuthServlet.getMessage(request, null);
            OAuthManager omgr = WebloggerFactory.getWeblogger().getOAuthManager();            
            OAuthAccessor accessor = omgr.getAccessor(requestMessage);

            if (accessor == null) {

                OAuthConsumer consumer = omgr.getConsumer(requestMessage);
                accessor = new OAuthAccessor(consumer);
                omgr.getValidator().validateMessage(requestMessage, accessor);

                {
                    // Support the 'Variable Accessor Secret' extension
                    // described in http://oauth.pbwiki.com/AccessorSecret
                    String secret = requestMessage.getParameter("oauth_accessor_secret");
                    if (secret != null) {
                        accessor.setProperty(OAuthConsumer.ACCESSOR_SECRET, secret);
                    }
                }

                // generate request_token and secret
                omgr.generateRequestToken(accessor);
                WebloggerFactory.getWeblogger().flush();
            }

            response.setContentType("text/plain");
            OutputStream out = response.getOutputStream();
            String token = accessor.requestToken != null
                    ? accessor.requestToken: accessor.accessToken;
            OAuth.formEncode(OAuth.newList(
                    "oauth_token", token,
                    "oauth_token_secret", accessor.tokenSecret), out);
            out.close();
            
        } catch (Exception e){
            handleException(e, request, response, true);
        }
        
    }
