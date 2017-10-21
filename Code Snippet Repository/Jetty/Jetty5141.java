    public static void config()
    {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        URL url = cl.getResource("logging.properties");
        if (url != null)
        {
            System.err.printf("Initializing java.util.logging from %s%n",url);
            try (InputStream in = url.openStream())
            {
                LogManager.getLogManager().readConfiguration(in);
            }
            catch (IOException e)
            {
                e.printStackTrace(System.err);
            }
        } 
        else 
        {
            System.err.printf("WARNING: java.util.logging failed to initialize: logging.properties not found%n");
        }

        System.setProperty("org.apache.commons.logging.Log","org.apache.commons.logging.impl.Jdk14Logger");
    }
