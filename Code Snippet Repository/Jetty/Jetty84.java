    public void parse (Set<? extends Handler> handlers, String className) throws Exception
    {
        if (className == null)
            return;

        String tmp = className;
        className = className.replace('.', '/')+".class";
        URL resource = Loader.getResource(className);
        if (resource!= null)
        {
            Resource r = Resource.newResource(resource);
            addParsedClass(tmp, r);
            try (InputStream is = r.getInputStream())
            {
                scanClass(handlers, null, is);
            }
        }
    }
