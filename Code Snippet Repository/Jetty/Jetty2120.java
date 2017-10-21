    private static boolean annotationsAvailable()
    {
        boolean result = false;
        try
        {
            Loader.loadClass(AbstractWebAppProvider.class,"org.eclipse.jetty.annotations.AnnotationConfiguration");
            result = true;
            LOG.debug("Annotation support detected");
        }
        catch (ClassNotFoundException e)
        {
            result = false;
            LOG.debug("No annotation support detected");
        }

        return result;
    }
