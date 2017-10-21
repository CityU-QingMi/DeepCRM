    public ClassLoader getClassLoader()
    {
        int cnt = elements.size();
        URL[] urls = new URL[cnt];
        for (int i = 0; i < cnt; i++)
        {
            try
            {
                urls[i] = elements.get(i).toURI().toURL();
                StartLog.debug("URLClassLoader.url[%d] = %s",i,urls[i]);
            }
            catch (MalformedURLException e)
            {
                StartLog.warn(e);
            }
        }
        StartLog.debug("Loaded %d URLs into URLClassLoader",urls.length);

        ClassLoader parent = Thread.currentThread().getContextClassLoader();
        if (parent == null)
        {
            parent = Classpath.class.getClassLoader();
        }
        if (parent == null)
        {
            parent = ClassLoader.getSystemClassLoader();
        }
        return new Loader(urls,parent);
    }
