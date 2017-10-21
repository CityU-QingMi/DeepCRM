    protected boolean validateUser(String username, String password)
    throws Exception {
        boolean authenticated = false;
        boolean enabled = false;
        boolean apiEnabled = false;
        User user = null;
        try {
            
            UserManager userMgr = WebloggerFactory.getWeblogger().getUserManager();
            user = userMgr.getUserByUserName(username);
            
            enabled = user.getEnabled();
            if (enabled) {
                // are passwords encrypted?
                String encrypted =
                        WebloggerConfig.getProperty("passwds.encryption.enabled");
                //System.out.print("password was [" + password + "] ");
                if ("true".equalsIgnoreCase(encrypted)) {
                    password = Utilities.encodePassword(password,
                            WebloggerConfig.getProperty("passwds.encryption.algorithm"));
                }
                //System.out.println("is now [" + password + "]");
                authenticated = user.getPassword().equals(password);
                
                apiEnabled = WebloggerRuntimeConfig.getBooleanProperty("webservices.enableXmlRpc");
            }
        } catch (Exception e) {
            mLogger.error("ERROR internal error validating user", e);
        }
        
        if ( !enabled ) {
            throw new XmlRpcNotAuthorizedException(USER_DISABLED_MSG);
        }
        
        if ( !authenticated ) {
            throw new XmlRpcNotAuthorizedException(AUTHORIZATION_EXCEPTION_MSG);
        }
        
        if ( !apiEnabled ) {
            throw new XmlRpcNotAuthorizedException(BLOGGERAPI_DISABLED_MSG);
        }        
        
        return authenticated;
    }
