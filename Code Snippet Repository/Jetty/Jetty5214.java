    static Resource newResource(URL url, boolean useCaches)
    {
        if (url==null)
            return null;

        String url_string=url.toExternalForm();
        if( url_string.startsWith( "file:"))
        {
            try
            {
                return new PathResource(url);
            }
            catch(Exception e)
            {
                LOG.warn(e.toString());
                LOG.debug(Log.EXCEPTION,e);
                return new BadResource(url,e.toString());
            }
        }
        else if( url_string.startsWith( "jar:file:"))
        {
            return new JarFileResource(url, useCaches);
        }
        else if( url_string.startsWith( "jar:"))
        {
            return new JarResource(url, useCaches);
        }

        return new URLResource(url,null,useCaches);
    }
