    public MavenProject build( File pom, ProjectBuilderConfiguration configuration )
        throws ProjectBuildingException
    {
        ProjectBuildingRequest request = injectSession( toRequest( configuration ) );

        try
        {
            return projectBuilder.build( pom, request ).getProject();
        }
        catch ( ProjectBuildingException e )
        {
            throw transformError( e );
        }
    }
