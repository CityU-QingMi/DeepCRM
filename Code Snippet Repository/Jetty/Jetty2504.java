    private static void add(List<PathAttribute>paths,List<URIAttribute> uris,String key,int weight)
    {
        String value = System.getProperty(key);
        if (value!=null)
        {
            Path path = toCanonicalPath(value);
            paths.add(new PathAttribute(key,path,weight));
            uris.add(new URIAttribute(key+".uri",toCanonicalURI(path.toUri()),weight));
        }
    }
