        protected String getPathsToRequiredBundles (String requireTldBundles)
        throws Exception
        {
            if (requireTldBundles == null) return null;

            ServiceReference ref = _bundle.getBundleContext().getServiceReference(org.osgi.service.packageadmin.PackageAdmin.class.getName());
            PackageAdmin packageAdmin = (ref == null) ? null : (PackageAdmin)_bundle.getBundleContext().getService(ref);
            if (packageAdmin == null)
                throw new IllegalStateException("Unable to get PackageAdmin reference to locate required Tld bundles");

            StringBuilder paths = new StringBuilder();         
            String[] symbNames = requireTldBundles.split("[, ]");

            for (String symbName : symbNames)
            {
                Bundle[] bs = packageAdmin.getBundles(symbName, null);
                if (bs == null || bs.length == 0) 
                { 
                    throw new IllegalArgumentException("Unable to locate the bundle '" + symbName
                                                       + "' specified by "
                                                       + OSGiWebappConstants.REQUIRE_TLD_BUNDLE
                                                       + " in manifest of "
                                                       + (_bundle == null ? "unknown" : _bundle.getSymbolicName())); 
                }

                File f = BundleFileLocatorHelperFactory.getFactory().getHelper().getBundleInstallLocation(bs[0]);
                if (paths.length() > 0) paths.append(", ");
                paths.append(f.toURI().toURL().toString());
                LOG.debug("getPathsToRequiredBundles: bundle path=" + bs[0].getLocation() + " uri=" + f.toURI());
            }

            return paths.toString();
        }
