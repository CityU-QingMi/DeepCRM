    public DefaultModelBuildingRequest( ModelBuildingRequest request )
    {
        setPomFile( request.getPomFile() );
        setModelSource( request.getModelSource() );
        setValidationLevel( request.getValidationLevel() );
        setProcessPlugins( request.isProcessPlugins() );
        setTwoPhaseBuilding( request.isTwoPhaseBuilding() );
        setProfiles( request.getProfiles() );
        setActiveProfileIds( request.getActiveProfileIds() );
        setInactiveProfileIds( request.getInactiveProfileIds() );
        setSystemProperties( request.getSystemProperties() );
        setUserProperties( request.getUserProperties() );
        setBuildStartTime( request.getBuildStartTime() );
        setModelResolver( request.getModelResolver() );
        setModelBuildingListener( request.getModelBuildingListener() );
        setModelCache( request.getModelCache() );
    }
