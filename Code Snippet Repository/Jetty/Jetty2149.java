    @Override
    protected void doStart() throws Exception
    {

        BundleContext bundleContext = FrameworkUtil.getBundle(this.getClass()).getBundleContext();

        //Start a tracker to find webapps that are osgi services that are targetted to my server name
        _tracker = new ContextTracker (bundleContext, 
                                       Util.createFilter(bundleContext, ContextHandler.class.getName(), getServerInstanceWrapper().getManagedServerName()));
        _tracker.open();

        
        //register as an osgi service for deploying contexts defined in a bundle, advertising the name of the jetty Server instance we are related to
        Dictionary<String,String> properties = new Hashtable<String,String>();
        properties.put(OSGiServerConstants.MANAGED_JETTY_SERVER_NAME, getServerInstanceWrapper().getManagedServerName());
        
        //register as an osgi service for deploying contexts, advertising the name of the jetty Server instance we are related to
        _serviceRegForServices = FrameworkUtil.getBundle(this.getClass()).getBundleContext().registerService(ServiceProvider.class.getName(), this, properties);
        super.doStart();
    }
