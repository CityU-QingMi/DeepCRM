    @ManagedOperation(value = "", impact = "")
    public Collection<String> getApps()
    {
        List<String> ret = new ArrayList<>();
        for (DeploymentManager.AppEntry entry : _manager.getAppEntries())
        {
            ret.add(toRef(entry.getApp()));
        }
        return ret;
    }
