    @Override
    protected List<Resource> findJars (WebAppContext context) 
    throws Exception
    {
        List<Resource> mergedResources = new ArrayList<Resource>();
        //get jars from WEB-INF/lib if there are any
        List<Resource> webInfJars = super.findJars(context);
        if (webInfJars != null)
            mergedResources.addAll(webInfJars);
        
        //add fragment jars and any Required-Bundles as if in WEB-INF/lib of the associated webapp
        Bundle[] bundles = PackageAdminServiceTracker.INSTANCE.getFragmentsAndRequiredBundles((Bundle)context.getAttribute(OSGiWebappConstants.JETTY_OSGI_BUNDLE));
        if (bundles != null && bundles.length > 0)
        {
            Set<Bundle> fragsAndReqsBundles = (Set<Bundle>)context.getAttribute(FRAGMENT_AND_REQUIRED_BUNDLES);
            if (fragsAndReqsBundles == null)
            {
                fragsAndReqsBundles = new HashSet<Bundle>();
                context.setAttribute(FRAGMENT_AND_REQUIRED_BUNDLES, fragsAndReqsBundles);
            }
            
            Set<Resource> fragsAndReqsResources = (Set<Resource>)context.getAttribute(FRAGMENT_AND_REQUIRED_RESOURCES);
            if (fragsAndReqsResources == null)
            {
                fragsAndReqsResources = new HashSet<Resource>();
                context.setAttribute(FRAGMENT_AND_REQUIRED_RESOURCES, fragsAndReqsResources);
            }
            
            for (Bundle b : bundles)
            {
                //skip bundles that are not installed
                if (b.getState() == Bundle.UNINSTALLED)
                    continue;
                
                //add to context attribute storing associated fragments and required bundles
                fragsAndReqsBundles.add(b);
                File f = BundleFileLocatorHelperFactory.getFactory().getHelper().getBundleInstallLocation(b);
                Resource r = Resource.newResource(f.toURI());
                //add to convenience context attribute storing fragments and required bundles as Resources
                fragsAndReqsResources.add(r);
                mergedResources.add(r);
            }
        }
        
        return mergedResources;
    }
