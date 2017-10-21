    public void updateScopeCurrentPom( Artifact artifact, String ignoredScope )
    {
        logger.debug( indent + artifact + " (not setting artifactScope to: " + ignoredScope + "; local artifactScope "
            + artifact.getScope() + " wins)" );

        // TODO better way than static? this might hide messages in a reactor
        if ( !ignoredArtifacts.contains( artifact ) )
        {
            logger.warn( "\n\tArtifact " + artifact + " retains local artifactScope '" + artifact.getScope()
                + "' overriding broader artifactScope '" + ignoredScope + "'\n"
                + "\tgiven by a dependency. If this is not intended, modify or remove the local artifactScope.\n" );
            ignoredArtifacts.add( artifact );
        }
    }
