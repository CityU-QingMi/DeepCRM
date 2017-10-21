    public void addApp(App app)
    {
        LOG.debug("Deployable added: {}",app.getOriginId());
        AppEntry entry = new AppEntry();
        entry.app = app;
        entry.setLifeCycleNode(_lifecycle.getNodeByName("undeployed"));
        _apps.add(entry);

        if (isRunning() && _defaultLifeCycleGoal != null)
        {
            // Immediately attempt to go to default lifecycle state
            this.requestAppGoal(entry,_defaultLifeCycleGoal);
        }
    }
