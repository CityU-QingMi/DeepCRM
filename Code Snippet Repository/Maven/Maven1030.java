        @Override
        public void afterProjectsRead( MavenSession session )
        {
            MavenProject project = session.getProjects().get( 0 );

            Dependency dependency = new Dependency();
            dependency.setArtifactId( INJECTED_ARTIFACT_ID );
            dependency.setGroupId( "foo" );
            dependency.setVersion( "1.2.3" );
            dependency.setScope( "system" );
            try
            {
                dependency.setSystemPath( new File(
                                                    "src/test/projects/lifecycle-executor/project-with-additional-lifecycle-elements/pom.xml" ).getCanonicalPath() );
            }
            catch ( IOException e )
            {
                throw new RuntimeException( e );
            }

            project.getModel().addDependency( dependency );
        }
