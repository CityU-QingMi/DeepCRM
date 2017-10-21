    private void startProxy(Map<String, String> params) throws Exception
    {
        proxy = new Server();

        HttpConfiguration configuration = new HttpConfiguration();
        configuration.setSendDateHeader(false);
        configuration.setSendServerVersion(false);
        proxyConnector = new ServerConnector(proxy, new HttpConnectionFactory(configuration));
        proxy.addConnector(proxyConnector);

        ServletContextHandler proxyContext = new ServletContextHandler(proxy, "/", true, false);
        ServletHolder proxyServletHolder = new ServletHolder(new AsyncMiddleManServlet()
        {
            @Override
            protected String rewriteTarget(HttpServletRequest clientRequest)
            {
                StringBuilder builder = new StringBuilder();
                builder.append(clientRequest.getScheme()).append("://127.0.0.1:");
                builder.append(serverConnector.getLocalPort());
                builder.append(clientRequest.getRequestURI());
                String query = clientRequest.getQueryString();
                if (query != null)
                    builder.append("?").append(query);
                return builder.toString();
            }
        });
        if (params != null)
            proxyServletHolder.setInitParameters(params);

        proxyContext.addServlet(proxyServletHolder, "/*");

        proxy.start();
    }
