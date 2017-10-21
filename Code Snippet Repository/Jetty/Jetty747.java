    @ManagedAttribute("")
    public Collection<App> getApps()
    {
        List<App> ret = new ArrayList<  >();
        for (AppEntry entry : _apps)
        {
            ret.add(entry.app);
        }
        return ret;
    }
