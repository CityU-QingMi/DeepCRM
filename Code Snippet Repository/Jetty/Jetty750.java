    public void removeApp(App app)
    {
        Iterator<AppEntry> it = _apps.iterator();
        while (it.hasNext())
        {
            AppEntry entry = it.next();
            if (entry.app.equals(app))
            {
                if (! AppLifeCycle.UNDEPLOYED.equals(entry.lifecyleNode.getName()))
                    requestAppGoal(entry.app,AppLifeCycle.UNDEPLOYED);
                it.remove();
                LOG.debug("Deployable removed: {}",entry.app);
            }
        }
    }
