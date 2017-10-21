    public DefaultProjectBuildingRequest()
    {
        processPlugins = true;
        profiles = new ArrayList<>();
        activeProfileIds = new ArrayList<>();
        inactiveProfileIds = new ArrayList<>();
        systemProperties = new Properties();
        userProperties = new Properties();
        remoteRepositories = new ArrayList<>();
        pluginArtifactRepositories = new ArrayList<>();
    }
