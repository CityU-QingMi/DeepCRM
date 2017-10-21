    @Override
    public Resource getJarFor(ServletContainerInitializer service) throws MalformedURLException, IOException
    {
        Resource resource = super.getJarFor(service);
        // TODO This is not correct, but implemented like this to be bug for bug compatible
        // with previous implementation that could only handle actual jars and not bundles.
        if (resource!=null && !resource.toString().endsWith(".jar"))
            return null;
        return resource;
    }
