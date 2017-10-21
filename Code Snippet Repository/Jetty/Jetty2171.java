    public URL getResource(String name)
    {
        URL url= null;
        boolean tried_parent= false;

        
        if (url == null)
        {           
            url = _osgiBundleClassLoader.getResource(name);

            if (url == null && name.startsWith("/"))
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("HACK leading / off " + name);

                url = _osgiBundleClassLoader.getResource(name.substring(1));
            }
        }

        if (url == null && !tried_parent)
        {
            if (_parent!=null)
                url= _parent.getResource(name);
        }

        if (url != null)
            if (LOG.isDebugEnabled())
                LOG.debug("getResource("+name+")=" + url);

        return url;
    }
