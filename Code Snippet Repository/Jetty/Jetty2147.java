    public boolean serviceAdded (ServiceReference serviceRef, ContextHandler context)
    {
        if (context == null || serviceRef == null)
            return false;
        
        if (context instanceof org.eclipse.jetty.webapp.WebAppContext)
            return false; //the ServiceWebAppProvider will deploy it
        
        String watermark = (String)serviceRef.getProperty(OSGiWebappConstants.WATERMARK);
        if (watermark != null && !"".equals(watermark))
            return false;  //this service represents a contexthandler that has already been registered as a service by another of our deployers
        
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(getServerInstanceWrapper().getParentClassLoaderForWebapps());
        try
        {
            //See if there is a context file to apply to this pre-made context
            String contextFile = (String)serviceRef.getProperty(OSGiWebappConstants.JETTY_CONTEXT_FILE_PATH);
            if (contextFile == null)
                contextFile = (String)serviceRef.getProperty(OSGiWebappConstants.SERVICE_PROP_CONTEXT_FILE_PATH); 
                  
            String[] keys = serviceRef.getPropertyKeys();
            Dictionary properties = new Hashtable<String, Object>();
            if (keys != null)
            {
                for (String key:keys)
                    properties.put(key, serviceRef.getProperty(key));
            }
            Bundle bundle = serviceRef.getBundle();                
            String originId = bundle.getSymbolicName() + "-" + bundle.getVersion().toString() + "-"+(contextFile!=null?contextFile:serviceRef.getProperty(Constants.SERVICE_ID));
            ServiceApp app = new ServiceApp(getDeploymentManager(), this, bundle, properties, contextFile, originId);         
            app.setHandler(context); //set the pre=made ContextHandler instance
            _serviceMap.put(serviceRef, app);
            getDeploymentManager().addApp(app);
            return true;
        }
        finally
        {
            Thread.currentThread().setContextClassLoader(cl); 
        }
    }
