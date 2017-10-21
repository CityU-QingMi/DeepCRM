    public Collection<App> getApps(Node node)
    {
        List<App> ret = new ArrayList<>();
        for (AppEntry entry : _apps)
        {
            if (entry.lifecyleNode == node)
            {
                ret.add(entry.app);
            }
        }
        return ret;
    }
