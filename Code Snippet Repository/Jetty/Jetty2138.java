    public void start(final BundleContext context) throws Exception
    {
        INSTANCE = this;

        // track other bundles and fragments attached to this bundle that we
        // should activate.
        _packageAdminServiceTracker = new PackageAdminServiceTracker(context);

        // track jetty Server instances that we should support as deployment targets
        _jettyServerServiceTracker = new ServiceTracker(context, context.createFilter("(objectclass=" + Server.class.getName() + ")"), new JettyServerServiceTracker());
        _jettyServerServiceTracker.open();
        
        // Create a default jetty instance right now.
        Server defaultServer = DefaultJettyAtJettyHomeHelper.startJettyAtJettyHome(context);
    }
