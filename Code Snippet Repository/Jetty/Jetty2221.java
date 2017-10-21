    protected static Bundle getBundle(BundleContext bundleContext, String symbolicName)
    {
            Map<String,Bundle> _bundles = new HashMap<String, Bundle>();
            for (Bundle b : bundleContext.getBundles())
            {
                Bundle prevBundle = _bundles.put(b.getSymbolicName(), b);
                String err = prevBundle != null ? "2 versions of the bundle " + b.getSymbolicName()
                                                + " "
                                                + b.getHeaders().get("Bundle-Version")
                                                + " and "
                                                + prevBundle.getHeaders().get("Bundle-Version") : "";
                                                Assert.assertNull(err, prevBundle);
            }
        return _bundles.get(symbolicName);
    }
