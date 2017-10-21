    private ClassLoader getBundleClassLoaderForContainer (Bundle bundle)
    {
        checkContainerType (bundle);
        if (osgiContainer == null)
        {
            LOG.warn("No classloader for unknown OSGi container type");
            return null;
        }
        
        switch (osgiContainer)
        {
            case EquinoxOld:
            case EquinoxLuna:
            {
                return internalGetEquinoxBundleClassLoader(bundle);
            }

            case FelixOld:
            case Felix403:
            {
                return internalGetFelixBundleClassLoader(bundle); 
            }

            case Concierge:
            {
                return internalGetConciergeBundleClassLoader(bundle); 
            }

            default:
            {
                LOG.warn("No classloader found for bundle "+bundle.getSymbolicName());
                return null;

            }
        }
    }
