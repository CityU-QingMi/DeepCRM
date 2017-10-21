    public boolean bundleRemoved (Bundle bundle) throws Exception
    {
        List<App> apps = _bundleMap.remove(bundle);
        boolean removed = false;
        if (apps != null)
        {
            for (App app:apps)
            {
                _appMap.remove(app.getOriginId());
                getDeploymentManager().removeApp(app);
                removed = true;
            }
        }
        return removed; //true if even 1 context was removed associated with this bundle
    }
