        private void injectReactorDependency( List<MavenProject> projects, String moduleFrom, String moduleTo )
        {
            for ( MavenProject project : projects )
            {
                if ( moduleFrom.equals( project.getArtifactId() ) )
                {
                    Dependency dependency = new Dependency();
                    dependency.setArtifactId( moduleTo );
                    dependency.setGroupId( project.getGroupId() );
                    dependency.setVersion( project.getVersion() );

                    project.getModel().addDependency( dependency );
                }
            }
        }
