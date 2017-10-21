    protected void doStart() throws Exception
    {
        _webappTracker = new WebAppTracker(FrameworkUtil.getBundle(this.getClass()).getBundleContext(), getServerInstanceWrapper().getManagedServerName());
        _webappTracker.open();
        //register as an osgi service for deploying bundles, advertising the name of the jetty Server instance we are related to
        Dictionary<String,String> properties = new Hashtable<String,String>();
        properties.put(OSGiServerConstants.MANAGED_JETTY_SERVER_NAME, getServerInstanceWrapper().getManagedServerName());
        _serviceRegForBundles = FrameworkUtil.getBundle(this.getClass()).getBundleContext().registerService(BundleProvider.class.getName(), this, properties);
        super.doStart();
    }
