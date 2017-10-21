    public Bundle[] getFragments(Bundle bundle)
    {
        ServiceReference sr = _context.getServiceReference(PackageAdmin.class.getName());
        if (sr == null)
        {// we should never be here really.
            return null;
        }
        PackageAdmin admin = (PackageAdmin) _context.getService(sr);
        return admin.getFragments(bundle);
    }
