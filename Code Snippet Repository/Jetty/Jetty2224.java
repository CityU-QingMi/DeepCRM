    protected static void debugBundles(BundleContext bundleContext)
    {
        Map<String, Bundle> bundlesIndexedBySymbolicName = new HashMap<String, Bundle>();
        System.err.println("Active " + Bundle.ACTIVE);
        System.err.println("RESOLVED " + Bundle.RESOLVED);
        System.err.println("INSTALLED " + Bundle.INSTALLED);
        for (Bundle b : bundleContext.getBundles())
        {
            bundlesIndexedBySymbolicName.put(b.getSymbolicName(), b);
            System.err.println("    " + b.getBundleId()+" "+b.getSymbolicName() + " " + b.getLocation() + " " + b.getVersion()+ " " + b.getState());
        }
    }
