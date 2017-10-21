    public void preConfigure() throws Exception
    {
        // Setup configurations
        loadConfigurations();

        // Setup system classes
        loadSystemClasses();

        // Setup server classes
        loadServerClasses();

        // Configure classloader
        _ownClassLoader=false;
        if (getClassLoader()==null)
        {
            WebAppClassLoader classLoader = new WebAppClassLoader(this);
            setClassLoader(classLoader);
            _ownClassLoader=true;
        }

        if (LOG.isDebugEnabled())
        {
            ClassLoader loader = getClassLoader();
            LOG.debug("Thread Context classloader {}",loader);
            loader=loader.getParent();
            while(loader!=null)
            {
                LOG.debug("Parent class loader: {} ",loader);
                loader=loader.getParent();
            }
        }

        // Prepare for configuration
        for (Configuration configuration : _configurations)
        {
            LOG.debug("preConfigure {} with {}",this,configuration);
            configuration.preConfigure(this);
        }
    }
