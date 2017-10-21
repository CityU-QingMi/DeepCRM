    public synchronized void loadPackages() throws ConfigurationException {
        if (LOG.isTraceEnabled())
            LOG.trace("Loading packages from XML and Convention on startup");                

        //init action context
        ActionContext ctx = ActionContext.getContext();
        if (ctx == null) {
            ctx = createActionContext();
            ActionContext.setContext(ctx);
        }

        Set<String> bundleNames = new HashSet<>();

        //iterate over the bundles and load packages from them
        for (Bundle bundle : osgiHost.getBundles().values()) {
            String bundleName = bundle.getSymbolicName();
            if (shouldProcessBundle(bundle) && !bundleNames.contains(bundleName)) {
                bundleNames.add(bundleName);
                //load XML and Convention config
                loadConfigFromBundle(bundle);
            }
        }

        bundlesChanged = false;
        bundleContext.addBundleListener(this);
    }
