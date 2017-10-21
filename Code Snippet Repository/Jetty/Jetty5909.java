    public void scan (Pattern pattern, ClassLoader loader, boolean isNullInclusive, boolean visitParent)
    throws Exception
    {
        while (loader!=null)
        {
            if (loader instanceof URLClassLoader)
            {
                URL[] urls = ((URLClassLoader)loader).getURLs();
                if (urls != null)
                {
                    URI[] uris = new URI[urls.length];
                    int i=0;
                    for (URL u : urls)
                        uris[i++] = u.toURI();
                    scan (pattern, uris, isNullInclusive);
                }
            }     
            if (visitParent)
                loader=loader.getParent();
            else
                loader = null;
        }  
    }
