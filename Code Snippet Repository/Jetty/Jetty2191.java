    protected void collectFragmentsAndRequiredBundles(Bundle bundle, PackageAdmin admin, Map<String, Bundle> deps, boolean onlyReexport)
    {
        Bundle[] fragments = admin.getFragments(bundle);
        if (fragments != null)
        {
            // Also add the bundles required by the fragments.
            // this way we can inject onto an existing web-bundle a set of
            // bundles that extend it
            for (Bundle f : fragments)
            {
                if (!deps.keySet().contains(f.getSymbolicName()))
                {
                    deps.put(f.getSymbolicName(), f);
                    collectRequiredBundles(f, admin, deps, onlyReexport);
                }
            }
        }
        collectRequiredBundles(bundle, admin, deps, onlyReexport);
    }
