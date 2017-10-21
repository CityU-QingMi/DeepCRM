    public Bundle findJstlBundle ()
    {
        Class<?> jstlClass = null;
    
        try
        {
            jstlClass = JSTLBundleDiscoverer.class.getClassLoader().loadClass(DEFAULT_JSTL_BUNDLE_CLASS);
        }
        catch (ClassNotFoundException e)
        {
            LOG.info("jstl not on classpath", e);
        }
        
        if (jstlClass != null)
            //get the bundle containing jstl
            return FrameworkUtil.getBundle(jstlClass);
        
        return null;
    }
