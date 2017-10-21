    @ManagedOperation(value="", impact="")
    public void requestAppGoal(@Name("appId") String appId, @Name("nodeName") String nodeName)
    {
        AppEntry appentry = findAppByOriginId(appId);
        if (appentry == null)
        {
            throw new IllegalStateException("App not being tracked by Deployment Manager: " + appId);
        }
        requestAppGoal(appentry,nodeName);
    }
