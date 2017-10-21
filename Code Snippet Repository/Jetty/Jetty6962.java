    public static NativeWebSocketConfiguration getDefaultFrom(ServletContext context)
    {
        final String KEY = NativeWebSocketConfiguration.class.getName();
        
        NativeWebSocketConfiguration configuration = (NativeWebSocketConfiguration) context.getAttribute(KEY);
        if (configuration == null)
        {
            configuration = new NativeWebSocketConfiguration(context);
            context.setAttribute(KEY, configuration);
        }
        return configuration;
    }
