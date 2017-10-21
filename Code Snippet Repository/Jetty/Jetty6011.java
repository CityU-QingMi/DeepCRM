    public static URLStreamHandlerFactory getFactory()
    {
        try
        {
            // First, reset the factory field
            Field factoryField = getURLStreamHandlerFactoryField();
            factoryField.setAccessible(true);
            return (URLStreamHandlerFactory) factoryField.get(null);
        }
        catch(Throwable ignore)
        {
            return null;
        }
    }
