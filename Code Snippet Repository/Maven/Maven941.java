    @Override
    public ModelSource resolveModel( final Parent parent )
        throws UnresolvableModelException
    {
        try
        {
            final Artifact artifact = new DefaultArtifact( parent.getGroupId(), parent.getArtifactId(), "", "pom",
                                                           parent.getVersion() );

            final VersionRangeRequest versionRangeRequest = new VersionRangeRequest( artifact, repositories, context );
            versionRangeRequest.setTrace( trace );

            final VersionRangeResult versionRangeResult = resolver.resolveVersionRange( session, versionRangeRequest );

            if ( versionRangeResult.getHighestVersion() == null )
            {
                throw new UnresolvableModelException(
                    String.format( "No versions matched the requested parent version range '%s'",
                                   parent.getVersion() ),
                    parent.getGroupId(), parent.getArtifactId(), parent.getVersion() );

            }

            if ( versionRangeResult.getVersionConstraint() != null
                     && versionRangeResult.getVersionConstraint().getRange() != null
                     && versionRangeResult.getVersionConstraint().getRange().getUpperBound() == null )
            {
                // Message below is checked for in the MNG-2199 core IT.
                throw new UnresolvableModelException(
                    String.format( "The requested parent version range '%s' does not specify an upper bound",
                                   parent.getVersion() ),
                    parent.getGroupId(), parent.getArtifactId(), parent.getVersion() );

            }

            parent.setVersion( versionRangeResult.getHighestVersion().toString() );

            return resolveModel( parent.getGroupId(), parent.getArtifactId(), parent.getVersion() );
        }
        catch ( final VersionRangeResolutionException e )
        {
            throw new UnresolvableModelException( e.getMessage(), parent.getGroupId(), parent.getArtifactId(),
                                                  parent.getVersion(), e );

        }
    }
