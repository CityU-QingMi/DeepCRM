    public static HttpClient get(WebSocketContainerScope scope)
    {
        try
        {
            if (Class.forName("org.eclipse.jetty.xml.XmlConfiguration") != null)
            {
                Class<?> xmlClazz = Class.forName("org.eclipse.jetty.websocket.client.XmlBasedHttpClientProvider");
                Method getMethod = xmlClazz.getMethod("get", WebSocketContainerScope.class);
                Object ret = getMethod.invoke(null, scope);
                if ((ret != null) && (ret instanceof HttpClient))
                {
                    return (HttpClient) ret;
                }
            }
        }
        catch (Throwable ignore)
        {
            Log.getLogger(HttpClientProvider.class).ignore(ignore);
        }
        
        return DefaultHttpClientProvider.newHttpClient(scope);
    }
