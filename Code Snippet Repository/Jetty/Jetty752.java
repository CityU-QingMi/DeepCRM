    public void processBinding(Node node, App app) throws Exception
    {
        ContextHandler handler = app.getContextHandler();
        if (handler == null)
        {
            throw new NullPointerException("No Handler created for App: " + app);
        }

        if (handler instanceof WebAppContext)
        {
            WebAppContext context = (WebAppContext)handler;

            if (LOG.isDebugEnabled())
            {
                LOG.debug("Binding: Configuring webapp context with global settings from: " + _jettyXml);
            }

            if ( _jettyXml == null )
            {
                LOG.warn("Binding: global context binding is enabled but no jetty-web.xml file has been registered");
            }

            Resource globalContextSettings = Resource.newResource(_jettyXml);

            if (globalContextSettings.exists())
            {
                XmlConfiguration jettyXmlConfig = new XmlConfiguration(globalContextSettings.getInputStream());
                Resource resource = Resource.newResource(app.getOriginId());
                app.getDeploymentManager().scope(jettyXmlConfig,resource);
                jettyXmlConfig.configure(context);
            }
            else
            {
                LOG.info("Binding: Unable to locate global webapp context settings: " + _jettyXml);
            }
        }
    }
