    public void parse (Set<? extends Handler> handlers, List<String> classNames) throws Exception
    {
        MultiException me = new MultiException();

        for (String s:classNames)
        {
            try
            {
                String name = s;
                s = s.replace('.', '/')+".class";
                URL resource = Loader.getResource(s);
                if (resource!= null)
                {
                    Resource r = Resource.newResource(resource);
                    addParsedClass(name, r);
                    try (InputStream is = r.getInputStream())
                    {
                        scanClass(handlers, null, is);
                    }
                }
            }
            catch (Exception e)
            {
                me.add(new RuntimeException("Error scanning class "+s, e));
            }
        }
        me.ifExceptionThrow();
    }
