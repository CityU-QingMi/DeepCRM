    public BundleClassLoaderHelper getHelper()
    {
        //use the default
        BundleClassLoaderHelper helper = BundleClassLoaderHelper.DEFAULT;
        try
        {
            //if a fragment has not provided their own impl
            helper = (BundleClassLoaderHelper) Class.forName(BundleClassLoaderHelper.CLASS_NAME).newInstance();
        }
        catch (Throwable t)
        {
            LOG.ignore(t);
        }
        
        return helper;
    }
