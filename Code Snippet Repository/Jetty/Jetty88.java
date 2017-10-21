    public void parse (final Set<? extends Handler> handlers, Resource r) throws Exception
    {
        if (r == null)
            return;
        
        if (r.exists() && r.isDirectory())
        {
            parseDir(handlers, r);
            return;
        }

        String fullname = r.toString();
        if (fullname.endsWith(".jar"))
        {
            parseJar(handlers, r);
            return;
        }

        if (fullname.endsWith(".class"))
        {
            try (InputStream is=r.getInputStream())
            {
                scanClass(handlers, null, is);
                return;
            }
        }
        
        if (LOG.isDebugEnabled()) LOG.warn("Resource not scannable for classes: {}", r);
    }
