    public boolean bundleRemoved (Bundle bundle) throws Exception
    {
        App app = _bundleMap.remove(bundle);
        if (app != null)
        {
            getDeploymentManager().removeApp(app); 
            return true;
        }
        return false;
    }
