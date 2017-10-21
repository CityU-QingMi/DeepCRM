    public AppLifeCycle()
    {
        // Define Default Graph

        // undeployed -> deployed
        addEdge(UNDEPLOYED,DEPLOYING);
        addEdge(DEPLOYING,DEPLOYED);

        // deployed -> started
        addEdge(DEPLOYED,STARTING);
        addEdge(STARTING,STARTED);

        // started -> deployed
        addEdge(STARTED,STOPPING);
        addEdge(STOPPING,DEPLOYED);

        // deployed -> undeployed
        addEdge(DEPLOYED,UNDEPLOYING);
        addEdge(UNDEPLOYING,UNDEPLOYED);
    }
