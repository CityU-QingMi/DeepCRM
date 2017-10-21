    @Override
    protected void doStart() throws Exception
    {
        //Track bundles that are ContextHandlers that should be deployed
        _tracker = new ContextBundleTracker(FrameworkUtil.getBundle(this.getClass()).getBundleContext(), getServerInstanceWrapper().getManagedServerName());
        _tracker.open();
        
        //register as an osgi service for deploying contexts defined in a bundle, advertising the name of the jetty Server instance we are related to
        Dictionary<String,String> properties = new Hashtable<String,String>();
        properties.put(OSGiServerConstants.MANAGED_JETTY_SERVER_NAME, getServerInstanceWrapper().getManagedServerName());
        _serviceRegForBundles = FrameworkUtil.getBundle(this.getClass()).getBundleContext().registerService(BundleProvider.class.getName(), this, properties);
        super.doStart();
    }
