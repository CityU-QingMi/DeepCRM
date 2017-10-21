    public static void setFactory(URLStreamHandlerFactory factory)
    {
        try
        {
            // First, reset the factory field
            Field factoryField = getURLStreamHandlerFactoryField();
            factoryField.setAccessible(true);
            factoryField.set(null, null);
            
            if(factory != null)
            {
                // Next, set the factory
                URL.setURLStreamHandlerFactory(factory);
            }
        }
        catch(Throwable ignore)
        {
            // ignore.printStackTrace(System.err);
        }
    }
