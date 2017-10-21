    public void init(Configuration configuration) throws ConfigurationException {
        osgiHost = (OsgiHost) servletContext.getAttribute(StrutsOsgiListener.OSGI_HOST);
        bundleContext = osgiHost.getBundleContext();
        bundleAccessor.setBundleContext(bundleContext);
        bundleAccessor.setOsgiHost(osgiHost);
        this.configuration = configuration;

        //this class loader interface can be used by other plugins to lookup resources
        //from the bundles. A temporary class loader interface is set during other configuration
        //loading as well
        servletContext.setAttribute(ClassLoaderInterface.CLASS_LOADER_INTERFACE, new BundleClassLoaderInterface());
    }
