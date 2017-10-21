    private void installManagedBundles() {
        try {

            // Obtaining BundleContext from ServletContext class which is loaded
            // by bundle class loader
            BundleReference ref = (BundleReference) servletContext.getClass()
                    .getClassLoader();
            bctx = ref.getBundle().getBundleContext();

            // installing managed bundles
            installBundles();

        } catch (Exception ex) {
            LOG.error("Installing Managed Bundles met a problem", ex);
        }
    }
