    public Authenticator getAuthenticator(Server server, ServletContext context, AuthConfiguration configuration, IdentityService identityService, LoginService loginService)
    {
        Authenticator authenticator=null;
        try 
        {
            AuthConfigFactory authConfigFactory = AuthConfigFactory.getFactory();
            RegistrationListener listener = new RegistrationListener()
            {
                public void notify(String layer, String appContext)
                {}
            };

            Subject serviceSubject=findServiceSubject(server);
            String serverName=findServerName(server,serviceSubject);
            String contextPath=context.getContextPath();
            if (contextPath==null || contextPath.length()==0)
                contextPath="/";
            String appContext = serverName + " " + context.getContextPath();
            
            AuthConfigProvider authConfigProvider = authConfigFactory.getConfigProvider(MESSAGE_LAYER,appContext,listener);
  
            if (authConfigProvider != null)
            {
                ServletCallbackHandler servletCallbackHandler = new ServletCallbackHandler(loginService);
                ServerAuthConfig serverAuthConfig = authConfigProvider.getServerAuthConfig(MESSAGE_LAYER,appContext,servletCallbackHandler);
                if (serverAuthConfig != null)
                {
                    Map map = new HashMap();
                    for (String key : configuration.getInitParameterNames())
                        map.put(key,configuration.getInitParameter(key));
                    authenticator= new JaspiAuthenticator(serverAuthConfig,map,servletCallbackHandler,
                                serviceSubject,true, identityService);
                }
            }
        } 
        catch (AuthException e) 
        {
            LOG.warn(e);
        }
        return authenticator;
    }
