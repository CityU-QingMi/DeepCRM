    public static Resource newClassPathResource(String name,boolean useCaches,boolean checkParents)
    {
        URL url=Resource.class.getResource(name);
        
        if (url==null)
            url=Loader.getResource(name);
        if (url==null)
            return null;
        return newResource(url,useCaches);
    }
