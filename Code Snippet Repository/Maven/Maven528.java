    public void throwErrors( ArtifactResolutionRequest request, ArtifactResolutionResult result )
        throws ArtifactResolutionException
    {
        // Metadata cannot be found

        if ( result.hasMetadataResolutionExceptions() )
        {
            throw result.getMetadataResolutionException( 0 );
        }

        // Metadata cannot be retrieved

        // Cyclic Dependency Error

        if ( result.hasCircularDependencyExceptions() )
        {
            throw result.getCircularDependencyException( 0 );
        }

        // Version Range Violation

        if ( result.hasVersionRangeViolations() )
        {
            throw result.getVersionRangeViolation( 0 );
        }

        // Transfer Error

        if ( result.hasErrorArtifactExceptions() )
        {
            throw result.getErrorArtifactExceptions().get( 0 );
        }

        if ( result.hasMissingArtifacts() )
        {
            throw new MultipleArtifactsNotFoundException( request.getArtifact(), toList( result.getArtifacts() ),
                                                          result.getMissingArtifacts(),
                                                          request.getRemoteRepositories() );
        }

        // this should never happen since we checked all possible error sources before but better be sure
        if ( result.hasExceptions() )
        {
            throw new ArtifactResolutionException( "Unknown error during artifact resolution, " + request + ", "
                + result.getExceptions(), request.getArtifact(), request.getRemoteRepositories() );
        }
    }
