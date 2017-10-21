    public BundleFileLocatorHelper getHelper()
    {
        BundleFileLocatorHelper helper = BundleFileLocatorHelper.DEFAULT;
        try
        {
            //see if a fragment has supplied an alternative
            helper = (BundleFileLocatorHelper) Class.forName(BundleFileLocatorHelper.CLASS_NAME).newInstance();
        }
        catch (Throwable t)
        {
            LOG.ignore(t);
        }
        return helper;
    }
