    private static ClassLoader internalGetConciergeBundleClassLoader(Bundle bundle)
    {
        if (osgiContainer == OSGiContainerType.Concierge)
        {
            try
            {
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
                if (Concierge_BundleWiring_Class == null) {
                    Concierge_BundleWiring_Class = bundle.getClass().getClassLoader().loadClass("org.osgi.framework.wiring.BundleWiring");
                    Concierge_BundleImpl_Adapt_Method = Concierge_BundleImpl_Class.getMethod("adapt", new Class[] {Class.class});
                    Concierge_BundleImpl_Adapt_Method.setAccessible(true);
                    Concierge_BundleWiring_getClassLoader_Method = Concierge_BundleWiring_Class.getMethod("getClassLoader");
                    Concierge_BundleWiring_getClassLoader_Method.setAccessible(true);
                }

                Object wiring = Concierge_BundleImpl_Adapt_Method.invoke(bundle, new Object[] {Concierge_BundleWiring_Class});
                ClassLoader cl = (ClassLoader)Concierge_BundleWiring_getClassLoader_Method.invoke(wiring);
                return cl;
            }
            catch (Exception e)
            {
                LOG.warn(e);
                return null;
            }
        }

        LOG.warn("No classloader for Concierge platform for bundle "+bundle.getSymbolicName());
        return null;
    }
