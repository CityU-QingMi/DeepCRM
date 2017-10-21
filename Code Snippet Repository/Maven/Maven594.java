    public static MavenExecutionRequest copy( MavenExecutionRequest original )
    {
        DefaultMavenExecutionRequest copy = new DefaultMavenExecutionRequest();
        copy.setLocalRepository( original.getLocalRepository() );
        copy.setLocalRepositoryPath( original.getLocalRepositoryPath() );
        copy.setOffline( original.isOffline() );
        copy.setInteractiveMode( original.isInteractiveMode() );
        copy.setCacheNotFound( original.isCacheNotFound() );
        copy.setCacheTransferError( original.isCacheTransferError() );
        copy.setProxies( original.getProxies() );
        copy.setServers( original.getServers() );
        copy.setMirrors( original.getMirrors() );
        copy.setProfiles( original.getProfiles() );
        copy.setPluginGroups( original.getPluginGroups() );
        copy.setProjectPresent( original.isProjectPresent() );
        copy.setUserSettingsFile( original.getUserSettingsFile() );
        copy.setGlobalSettingsFile( original.getGlobalSettingsFile() );
        copy.setUserToolchainsFile( original.getUserToolchainsFile() );
        copy.setGlobalToolchainsFile( original.getGlobalToolchainsFile() );
        copy.setBaseDirectory( ( original.getBaseDirectory() != null ) ? new File( original.getBaseDirectory() )
                                                                       : null );
        copy.setGoals( original.getGoals() );
        copy.setRecursive( original.isRecursive() );
        copy.setPom( original.getPom() );
        copy.setSystemProperties( original.getSystemProperties() );
        copy.setUserProperties( original.getUserProperties() );
        copy.setShowErrors( original.isShowErrors() );
        copy.setActiveProfiles( original.getActiveProfiles() );
        copy.setInactiveProfiles( original.getInactiveProfiles() );
        copy.setTransferListener( original.getTransferListener() );
        copy.setLoggingLevel( original.getLoggingLevel() );
        copy.setGlobalChecksumPolicy( original.getGlobalChecksumPolicy() );
        copy.setUpdateSnapshots( original.isUpdateSnapshots() );
        copy.setRemoteRepositories( original.getRemoteRepositories() );
        copy.setPluginArtifactRepositories( original.getPluginArtifactRepositories() );
        copy.setRepositoryCache( original.getRepositoryCache() );
        copy.setWorkspaceReader( original.getWorkspaceReader() );
        copy.setNoSnapshotUpdates( original.isNoSnapshotUpdates() );
        copy.setExecutionListener( original.getExecutionListener() );
        copy.setUseLegacyLocalRepository( original.isUseLegacyLocalRepository() );
        copy.setBuilderId( original.getBuilderId() );
        return copy;
    }
