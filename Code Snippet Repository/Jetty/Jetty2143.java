    @Override
    public void preConfigure(final WebAppContext context) throws Exception
    {
        super.preConfigure(context);
        
        //Check to see if there have been any bundle symbolic names added of bundles that should be
        //regarded as being on the container classpath, and scanned for fragments, tlds etc etc.
        //This can be defined in:
        // 1. SystemProperty SYS_PROP_TLD_BUNDLES
        // 2. DeployerManager.setContextAttribute CONTAINER_BUNDLE_PATTERN
        String tmp = (String)context.getAttribute(CONTAINER_BUNDLE_PATTERN);
        Pattern pattern = (tmp==null?null:Pattern.compile(tmp));
        List<String> names = new ArrayList<String>();
        tmp = System.getProperty(SYS_PROP_TLD_BUNDLES);
        if (tmp != null)
        {
            StringTokenizer tokenizer = new StringTokenizer(tmp, ", \n\r\t", false);
            while (tokenizer.hasMoreTokens())
                names.add(tokenizer.nextToken());
        }
        HashSet<Resource> matchingResources = new HashSet<Resource>();
        if ( !names.isEmpty() || pattern != null)
        {
            Bundle[] bundles = FrameworkUtil.getBundle(OSGiWebInfConfiguration.class).getBundleContext().getBundles();
           
            for (Bundle bundle : bundles)
            {
                LOG.debug("Checking bundle {}:{}", bundle.getBundleId(), bundle.getSymbolicName());
                if (pattern != null)
                {
                    // if bundle symbolic name matches the pattern
                    if (pattern.matcher(bundle.getSymbolicName()).matches())
                    {
                        //get the file location of the jar and put it into the list of container jars that will be scanned for stuff (including tlds)
                        matchingResources.addAll(getBundleAsResource(bundle));
                    }
                }               
                if (names != null)
                {
                    //if there is an explicit bundle name, then check if it matches
                    if (names.contains(bundle.getSymbolicName()))
                        matchingResources.addAll(getBundleAsResource(bundle));
                }
            }
        }        
        for (Resource r:matchingResources)
        {
            context.getMetaData().addContainerResource(r);
        }
    }
