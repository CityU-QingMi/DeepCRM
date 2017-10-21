    public static ServerContainer configureContext(ServletContextHandler context) throws ServletException
    {
        // Create Basic components
        NativeWebSocketConfiguration nativeWebSocketConfiguration = NativeWebSocketServletContainerInitializer.getDefaultFrom(context.getServletContext());
        
        // Build HttpClient
        HttpClient httpClient = (HttpClient) context.getServletContext().getAttribute(HTTPCLIENT_ATTRIBUTE);
        if(httpClient == null)
        {
            httpClient = (HttpClient) context.getServer().getAttribute(HTTPCLIENT_ATTRIBUTE);
        }
        
        // Create the Jetty ServerContainer implementation
        ServerContainer jettyContainer = new ServerContainer(nativeWebSocketConfiguration, httpClient);
        context.addBean(jettyContainer);
        
        // Store a reference to the ServerContainer per javax.websocket spec 1.0 final section 6.4 Programmatic Server Deployment
        context.setAttribute(javax.websocket.server.ServerContainer.class.getName(),jettyContainer);
    
        // Create Filter
        if(isEnabledViaContext(context.getServletContext(), ADD_DYNAMIC_FILTER_KEY, true))
        {
            String instanceKey = WebSocketUpgradeFilter.class.getName() + ".SCI";
            if(context.getAttribute(instanceKey) == null)
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("Dynamic filter add to support JSR356/javax.websocket.server: {}", WebSocketUpgradeFilter.class.getName());
                WebSocketUpgradeFilter wsuf = WebSocketUpgradeFilter.configureContext(context);
                context.setAttribute(instanceKey, wsuf);
            }
        }
    
        return jettyContainer;
    }
