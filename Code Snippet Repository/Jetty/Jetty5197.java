    public static Resource getNonCachingResource (Resource resource)
    {
        if (!(resource instanceof JarFileResource))
            return resource;
        
        JarFileResource oldResource = (JarFileResource)resource;
        
        JarFileResource newResource = new JarFileResource(oldResource.getURL(), false);
        return newResource;
    }
