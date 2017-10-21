    public static URI getLocationOfClass(Class<?> clazz)
    {
        try
        {
            ProtectionDomain domain = clazz.getProtectionDomain();
            if (domain != null)
            {
                CodeSource source = domain.getCodeSource();
                if (source != null)
                {
                    URL location = source.getLocation();
                    
                    if (location != null)
                        return location.toURI();
                }
            }
            
            String resourceName = clazz.getName().replace('.', '/') + ".class";
            ClassLoader loader = clazz.getClassLoader();
            URL url = (loader == null ? ClassLoader.getSystemClassLoader() : loader).getResource(resourceName);
            if (url != null)
            {
                return URIUtil.getJarSource(url.toURI());
            }
        }
        catch (URISyntaxException e)
        {
            LOG.debug(e);
        }
        return null;
    }
