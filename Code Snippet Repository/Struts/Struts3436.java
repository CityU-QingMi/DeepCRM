    protected void startFelix() {
        //load properties from felix embedded file
        Properties configProps = getProperties("default.properties");

        // Copy framework properties from the system properties.
        Main.copySystemProperties(configProps);
        replaceSystemPackages(configProps);

        //struts, xwork and felix exported packages
        Properties strutsConfigProps = getProperties("struts-osgi.properties");
        addExportedPackages(strutsConfigProps, configProps);

        //find bundles and adde em to autostart property
        addAutoStartBundles(configProps);

        // Bundle cache
        String storageDir = System.getProperty("java.io.tmpdir") + ".felix-cache";
        configProps.setProperty(Constants.FRAMEWORK_STORAGE, storageDir);
        LOG.debug("Storing bundles at [{}]", storageDir);

        String cleanBundleCache = getServletContextParam("struts.osgi.clearBundleCache", "true");
        if ("true".equalsIgnoreCase(cleanBundleCache)) {
            LOG.debug("Clearing bundle cache");
            configProps.put(FelixConstants.FRAMEWORK_STORAGE_CLEAN, FelixConstants.FRAMEWORK_STORAGE_CLEAN_ONFIRSTINIT);
        }

        //other properties
        configProps.put(FelixConstants.SERVICE_URLHANDLERS_PROP, "false");
        configProps.put(FelixConstants.LOG_LEVEL_PROP, getServletContextParam("struts.osgi.logLevel", "1"));
        configProps.put(FelixConstants.BUNDLE_CLASSPATH, ".");
        configProps.put(FelixConstants.FRAMEWORK_BEGINNING_STARTLEVEL, getServletContextParam("struts.osgi.runLevel", "3"));

        try {
            felix = new Felix(configProps);
            felix.init();
            AutoProcessor.process(configProps, felix.getBundleContext());
            felix.start();

            LOG.trace("Apache Felix is running");
        }
        catch (Exception ex) {
            throw new ConfigurationException("Couldn't start Apache Felix", ex);
        }

        addSpringOSGiSupport();

        //add the bundle context to the ServletContext
        servletContext.setAttribute(OSGI_BUNDLE_CONTEXT, felix.getBundleContext());
    }
