    public PluginVersionResolutionException( String groupId, String artifactId, LocalRepository localRepository,
                                             List<RemoteRepository> remoteRepositories, String baseMessage )
    {
        super( "Error resolving version for plugin \'" + groupId + ":" + artifactId + "\' from the repositories "
            + format( localRepository, remoteRepositories ) + ": " + baseMessage );

        this.groupId = groupId;
        this.artifactId = artifactId;
        this.baseMessage = baseMessage;
    }
