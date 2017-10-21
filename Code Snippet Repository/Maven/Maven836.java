    public void startProcessChildren( Artifact artifact )
    {
        if ( wagonProvider == null )
        {
            if ( isLegacyCoreArtifact( artifact ) )
            {
                coreArtifacts.addFirst( artifact );
            }
            else if ( !coreArtifacts.isEmpty() && isWagonProvider( artifact ) )
            {
                wagonProvider = artifact;
                bannedArtifacts.put( artifact, null );
            }
        }
    }
