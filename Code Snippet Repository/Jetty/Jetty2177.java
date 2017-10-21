    public ClassLoader getBundleClassLoader(Bundle bundle)
    {
        String bundleActivator = (String) bundle.getHeaders().get("Bundle-Activator");
   
        if (bundleActivator == null)
        {
            bundleActivator = (String) bundle.getHeaders().get("Jetty-ClassInBundle");
        }
        if (bundleActivator != null)
        {
            try
            {
                return bundle.loadClass(bundleActivator).getClassLoader();
            }
            catch (ClassNotFoundException e)
            {
                LOG.warn(e);
            }
        }
        
        // resort to introspection     
        return getBundleClassLoaderForContainer(bundle);
    }
