    public void requestAppGoal(App app, String nodeName)
    {
        AppEntry appentry = findAppByOriginId(app.getOriginId());
        if (appentry == null)
        {
            throw new IllegalStateException("App not being tracked by Deployment Manager: " + app);
        }
        
        requestAppGoal(appentry,nodeName);
    }
