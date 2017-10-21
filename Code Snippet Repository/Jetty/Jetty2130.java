    public boolean bundleAdded (Bundle bundle) throws Exception
    {
        if (bundle == null)
            return false;

        //If the bundle defines a Web-ContextPath then its probably a webapp and the BundleWebAppProvider should deploy it
        if ((String)bundle.getHeaders().get(OSGiWebappConstants.RFC66_WEB_CONTEXTPATH) != null)
        {
            if (LOG.isDebugEnabled()) LOG.debug("BundleContextProvider ignoring bundle {} with {} set", bundle.getSymbolicName(), OSGiWebappConstants.RFC66_WEB_CONTEXTPATH);
            return false;
        }
        
        String contextFiles  = (String)bundle.getHeaders().get(OSGiWebappConstants.JETTY_CONTEXT_FILE_PATH);
        if (contextFiles == null)
            contextFiles = (String)bundle.getHeaders().get(OSGiWebappConstants.SERVICE_PROP_CONTEXT_FILE_PATH);
        
        if (contextFiles == null)
            return false;
        
        
        boolean added = false;
        //bundle defines JETTY_CONTEXT_FILE_PATH header,
        //a comma separated list of context xml files that each define a ContextHandler
        //TODO: (could be WebAppContexts)       
        String[] tmp = contextFiles.split("[,;]");
        for (String contextFile : tmp)
        {
            String originId = bundle.getSymbolicName() + "-" + bundle.getVersion().toString() + "-"+contextFile;
            OSGiApp app = new OSGiApp(getDeploymentManager(), this, originId, bundle, contextFile);
            _appMap.put(originId,app);
            List<App> apps = _bundleMap.get(bundle);
            if (apps == null)
            {
                apps = new ArrayList<App>();
                _bundleMap.put(bundle, apps);
            }
            apps.add(app);
            getDeploymentManager().addApp(app);
            added = true;
        }

        return added; //true if even 1 context from this bundle was added
    }
