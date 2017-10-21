    private void debugDependencyRequirements( List<MojoExecution> mojoExecutions )
    {
        Set<String> scopesToCollect = new TreeSet<>();
        Set<String> scopesToResolve = new TreeSet<>();

        for ( MojoExecution mojoExecution : mojoExecutions )
        {
            MojoDescriptor mojoDescriptor = mojoExecution.getMojoDescriptor();

            String scopeToCollect = mojoDescriptor.getDependencyCollectionRequired();
            if ( StringUtils.isNotEmpty( scopeToCollect ) )
            {
                scopesToCollect.add( scopeToCollect );
            }

            String scopeToResolve = mojoDescriptor.getDependencyResolutionRequired();
            if ( StringUtils.isNotEmpty( scopeToResolve ) )
            {
                scopesToResolve.add( scopeToResolve );
            }
        }

        logger.debug( "Dependencies (collect): " + scopesToCollect );
        logger.debug( "Dependencies (resolve): " + scopesToResolve );
    }
