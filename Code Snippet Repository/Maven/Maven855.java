    public PluginVersionResult resolve( PluginVersionRequest request )
        throws PluginVersionResolutionException
    {
        PluginVersionResult result = resolveFromProject( request );

        if ( result == null )
        {
            result = resolveFromRepository( request );

            if ( logger.isDebugEnabled() )
            {
                logger.debug( "Resolved plugin version for " + request.getGroupId() + ":" + request.getArtifactId()
                    + " to " + result.getVersion() + " from repository " + result.getRepository() );
            }
        }
        else if ( logger.isDebugEnabled() )
        {
            logger.debug( "Resolved plugin version for " + request.getGroupId() + ":" + request.getArtifactId() + " to "
                + result.getVersion() + " from POM " + request.getPom() );
        }

        return result;
    }
