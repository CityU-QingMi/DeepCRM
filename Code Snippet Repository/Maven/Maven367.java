    @Override
    public ProjectBuildingResult build( Artifact artifact, ProjectBuildingRequest request )
        throws ProjectBuildingException
    {
        if ( "maven-test".equals( artifact.getGroupId() ) )
        {
            String scope = artifact.getArtifactId().substring( "scope-".length() );

            try
            {
                artifact.setFile( ProjectClasspathTest.getFileForClasspathResource( ProjectClasspathTest.dir + "transitive-" + scope + "-dep.xml" ) );
            }
            catch ( FileNotFoundException e )
            {
                throw new IllegalStateException( "Missing test POM for " + artifact );
            }
        }
        if ( artifact.getFile() == null )
        {
            MavenProject project = new MavenProject();
            project.setArtifact( artifact );
            return new DefaultProjectBuildingResult( project, null, null );
        }
        return build( artifact.getFile(), request );
    }
