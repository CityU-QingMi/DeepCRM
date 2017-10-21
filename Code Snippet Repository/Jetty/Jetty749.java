    public List<App> getAppsWithSameContext(App app)
    {
        List<App> ret = new ArrayList<App>();
        if (app == null)
        {
            return ret;
        }

        String contextId = app.getContextPath();
        if (contextId == null)
        {
            // No context? Likely not deployed or started yet.
            return ret;
        }

        for (AppEntry entry : _apps)
        {
            if (entry.app.equals(app))
            {
                // Its the input app. skip it.
                // TODO: is this filter needed?
                continue;
            }

            if (contextId.equals(entry.app.getContextPath()))
            {
                ret.add(entry.app);
            }
        }
        return ret;
    }
