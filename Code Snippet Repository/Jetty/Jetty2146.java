    private void convertFragmentPathToResource (String resourcePath, Bundle fragment, Map<String, Resource> resourceMap )
    throws Exception
    {
        if (resourcePath == null)
            return;

        URL url = fragment.getEntry(resourcePath);
        if (url == null) 
        { 
            throw new IllegalArgumentException("Unable to locate " + resourcePath
                                               + " inside "
                                               + " the fragment '"
                                               + fragment.getSymbolicName()
                                               + "'"); 
        }
        url = BundleFileLocatorHelperFactory.getFactory().getHelper().getLocalURL(url);
        URI uri;
        try
        {
           uri = url.toURI();
        }
        catch (URISyntaxException e)
        {
            uri = new URI(url.toString().replaceAll(" ", "%20"));
        }
        String key = resourcePath.startsWith("/") ? resourcePath.substring(1) : resourcePath;
        resourceMap.put(key + ";" + fragment.getSymbolicName(), Resource.newResource(uri));
    }
