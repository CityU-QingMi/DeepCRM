    public MavenProject buildStandaloneSuperProject( ProjectBuilderConfiguration configuration )
        throws ProjectBuildingException
    {
        ProjectBuildingRequest request = injectSession( toRequest( configuration ) );
        request.setProcessPlugins( false );
        request.setValidationLevel( ModelBuildingRequest.VALIDATION_LEVEL_MINIMAL );

        ModelSource modelSource = new UrlModelSource( getClass().getResource( "standalone.xml" ) );

        MavenProject project = projectBuilder.build( modelSource, request ).getProject();
        project.setExecutionRoot( true );
        return project;
    }
