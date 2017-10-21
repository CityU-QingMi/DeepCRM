    protected static void assertAllBundlesActiveOrResolved(BundleContext bundleContext)
    {
        for (Bundle b : bundleContext.getBundles())
        {
            if (b.getState() == Bundle.INSTALLED)
            {
                diagnoseNonActiveOrNonResolvedBundle(b);
            }
            Assert.assertTrue("Bundle: " + b
                              + " (state should be "
                              + "ACTIVE["
                              + Bundle.ACTIVE
                              + "] or RESOLVED["
                              + Bundle.RESOLVED
                              + "]"
                              + ", but was ["
                              + b.getState()
                              + "])", (b.getState() == Bundle.ACTIVE) || (b.getState() == Bundle.RESOLVED));
        }
    }
