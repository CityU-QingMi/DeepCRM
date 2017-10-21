    @Override
    public boolean acceptWebSocket(WebSocketCreator creator, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        ClassLoader old = Thread.currentThread().getContextClassLoader();
        try
        {
            Thread.currentThread().setContextClassLoader(contextClassloader);
            
            // Create Servlet Specific Upgrade Request/Response objects
            ServletUpgradeRequest sockreq = new ServletUpgradeRequest(request);
            ServletUpgradeResponse sockresp = new ServletUpgradeResponse(response);
            
            Object websocketPojo = creator.createWebSocket(sockreq, sockresp);
            
            // Handle response forbidden (and similar paths)
            if (sockresp.isCommitted())
            {
                return false;
            }
            
            if (websocketPojo == null)
            {
                // no creation, sorry
                sockresp.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "Endpoint Creation Failed");
                return false;
            }
            
            // Allow Decorators to do their thing
            websocketPojo = getObjectFactory().decorate(websocketPojo);
            
            // Get the original HTTPConnection
            HttpConnection connection = (HttpConnection) request.getAttribute("org.eclipse.jetty.server.HttpConnection");
            
            // Send the upgrade
            EventDriver driver = eventDriverFactory.wrap(websocketPojo);
            return upgrade(connection, sockreq, sockresp, driver);
        }
        catch (URISyntaxException e)
        {
            throw new IOException("Unable to accept websocket due to mangled URI", e);
        }
        finally
        {
            Thread.currentThread().setContextClassLoader(old);
        }
    }
