    @Override 
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        
        try{
            OAuthMessage requestMessage = OAuthServlet.getMessage(request, null);
            
            OAuthManager omgr = WebloggerFactory.getWeblogger().getOAuthManager();
            OAuthAccessor accessor = omgr.getAccessor(requestMessage);

            String userId = request.getParameter("userId");
            if (userId == null) {
                userId = request.getParameter("xoauth_requestor_id");
            }
            
            if (userId == null) {
                // no user associted with the key, must be site-wide key,
                // so get user to login and do the authorization process
                sendToAuthorizePage(request, response, accessor);
            
            } else {

                // if consumer key is for specific user, check username match
                String consumerUserId = (String)accessor.consumer.getProperty("userId");
                if (consumerUserId != null && !userId.equals(consumerUserId)) {
                    throw new ServletException("ERROR: invalid or unspecified userId");
                }

                // set userId in accessor and mark it as authorized
                omgr.markAsAuthorized(accessor, userId);
                WebloggerFactory.getWeblogger().flush();
            }
            
            returnToConsumer(request, response, accessor);
            
        } catch (Exception e){
            handleException(e, request, response, true);
        }
    }
