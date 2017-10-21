        public ProjectBuilder( String groupId, String artifactId, String version )
        {
            Model model = new Model();
            model.setModelVersion( "4.0.0" );
            model.setGroupId( groupId );
            model.setArtifactId( artifactId );
            model.setVersion( version );
            model.setBuild(  new Build() );
            project = new MavenProject( model );
        }
