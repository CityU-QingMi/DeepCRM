    public List<ArtifactResult> resolveArtifacts( RepositorySystemSession session,
                                                  Collection<? extends ArtifactRequest> requests )
        throws ArtifactResolutionException
    {
        List<ArtifactResult> results = new ArrayList<>();

        for ( ArtifactRequest request : requests )
        {
            ArtifactResult result = new ArtifactResult( request );
            results.add( result );

            Artifact artifact = request.getArtifact();
            if ( "maven-test".equals( artifact.getGroupId() ) )
            {
                String scope = artifact.getArtifactId().substring( "scope-".length() );

                try
                {
                    artifact =
                        artifact.setFile( ProjectClasspathTest.getFileForClasspathResource( ProjectClasspathTest.dir
                            + "transitive-" + scope + "-dep.xml" ) );
                    result.setArtifact( artifact );
                }
                catch ( FileNotFoundException e )
                {
                    throw new IllegalStateException( "Missing test POM for " + artifact );
                }
            }
            else
            {
                result.addException( new ArtifactNotFoundException( artifact, null ) );
                throw new ArtifactResolutionException( results );
            }
        }

        return results;
    }
