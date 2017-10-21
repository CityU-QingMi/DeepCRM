    public PackageAdminServiceTracker(BundleContext context)
    {
        INSTANCE = this;
        _context = context;
        if (!setup())
        {
            try
            {
                _context.addServiceListener(this, "(objectclass=" + PackageAdmin.class.getName() + ")");
            }
            catch (InvalidSyntaxException e)
            {
                e.printStackTrace(); // won't happen
            }
        }
    }
