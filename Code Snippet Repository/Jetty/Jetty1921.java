    public Object getObjectInstance(Object url, Name name, Context ctx, Hashtable env)
        throws Exception
    {
        // null object means return a root context for doing resolutions
        if (url == null)
        {
            if(LOG.isDebugEnabled())LOG.debug(">>> new root context requested ");
            return new javaRootURLContext(env);
        }

        // return the resolution of the url
        if (url instanceof String)
        {
            if(LOG.isDebugEnabled())LOG.debug(">>> resolution of url "+url+" requested");
            Context rootctx = new javaRootURLContext (env);
            return rootctx.lookup ((String)url);
        }

        // return the resolution of at least one of the urls
        if (url instanceof String[])
        {
            if(LOG.isDebugEnabled())LOG.debug(">>> resolution of array of urls requested");
            String[] urls = (String[])url;
            Context rootctx = new javaRootURLContext (env);
            Object object = null;
            NamingException e = null;
            for (int i=0;(i< urls.length) && (object == null); i++)
            {
                try
                {
                    object = rootctx.lookup (urls[i]);
                }
                catch (NamingException x)
                {
                    e = x;
                }
            }

            if (object == null)
                throw e;
            else
                return object;
        }

        if(LOG.isDebugEnabled())LOG.debug(">>> No idea what to do, so return a new root context anyway");
        return new javaRootURLContext (env);
    }
