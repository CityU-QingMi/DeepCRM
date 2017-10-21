    public PluginPrefixResult resolve( PluginPrefixRequest request )
        throws NoPluginFoundForPrefixException
    {
        logger.debug( "Resolving plugin prefix " + request.getPrefix() + " from " + request.getPluginGroups() );

        PluginPrefixResult result = resolveFromProject( request );

        if ( result == null )
        {
            result = resolveFromRepository( request );

            if ( result == null )
            {
                throw new NoPluginFoundForPrefixException( request.getPrefix(), request.getPluginGroups(),
                                                           request.getRepositorySession().getLocalRepository(),
                                                           request.getRepositories() );
            }
            else if ( logger.isDebugEnabled() )
            {
                logger.debug( "Resolved plugin prefix " + request.getPrefix() + " to " + result.getGroupId() + ":"
                    + result.getArtifactId() + " from repository "
                    + ( result.getRepository() != null ? result.getRepository().getId() : "null" ) );
            }
        }
        else if ( logger.isDebugEnabled() )
        {
            logger.debug( "Resolved plugin prefix " + request.getPrefix() + " to " + result.getGroupId() + ":"
                + result.getArtifactId() + " from POM " + request.getPom() );
        }

        return result;
    }
