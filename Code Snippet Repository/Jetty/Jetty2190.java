    public Bundle[] getFragmentsAndRequiredBundles(Bundle bundle)
    {
        ServiceReference sr = _context.getServiceReference(PackageAdmin.class.getName());
        if (sr == null)
        {// we should never be here really.
            return null;
        }
        PackageAdmin admin = (PackageAdmin) _context.getService(sr);
        LinkedHashMap<String, Bundle> deps = new LinkedHashMap<String, Bundle>();
        collectFragmentsAndRequiredBundles(bundle, admin, deps, false);
        return deps.values().toArray(new Bundle[deps.size()]);
    }
