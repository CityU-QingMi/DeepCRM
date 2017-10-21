    public static HttpClient get(@SuppressWarnings("unused") WebSocketContainerScope scope)
    {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("jetty-websocket-httpclient.xml");
        if (resource == null)
        {
            return null;
        }
        
        try (InputStream in = resource.openStream())
        {
            XmlConfiguration configuration = new XmlConfiguration(in);
            return (HttpClient) configuration.configure();
        }
        catch (Throwable t)
        {
            Log.getLogger(XmlBasedHttpClientProvider.class).warn("Unable to load: " + resource, t);
        }
        
        return null;
    }
