    private boolean setup()
    {
        ServiceReference sr = _context.getServiceReference(PackageAdmin.class.getName());
        _fragmentsWereActivated = sr != null;
        if (sr != null) invokeFragmentActivators(sr);

        sr = _context.getServiceReference(StartLevel.class.getName());
        if (sr != null)
        {
            _startLevel = (StartLevel) _context.getService(sr);
            try
            {
                _maxStartLevel = Integer.parseInt(System.getProperty("osgi.startLevel", "6"));
            }
            catch (Exception e)
            {
                // nevermind default on the usual.
                _maxStartLevel = 6;
            }
        }
        return _fragmentsWereActivated;
    }
