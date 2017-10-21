    private ManagedVersionMap getManagedVersionsMap( Artifact originatingArtifact,
                                                     Map<String, Artifact> managedVersions )
    {
        ManagedVersionMap versionMap;
        if ( ( managedVersions != null ) && ( managedVersions instanceof ManagedVersionMap ) )
        {
            versionMap = (ManagedVersionMap) managedVersions;
        }
        else
        {
            versionMap = new ManagedVersionMap( managedVersions );
        }

        // remove the originating artifact if it is also in managed versions to avoid being modified during resolution
        Artifact managedOriginatingArtifact = versionMap.get( originatingArtifact.getDependencyConflictId() );

        if ( managedOriginatingArtifact != null )
        {
            // TODO we probably want to warn the user that he is building an artifact with
            // different values than in dependencyManagement
            if ( managedVersions instanceof ManagedVersionMap )
            {
                /* avoid modifying the managedVersions parameter creating a new map */
                versionMap = new ManagedVersionMap( managedVersions );
            }
            versionMap.remove( originatingArtifact.getDependencyConflictId() );
        }

        return versionMap;
    }
