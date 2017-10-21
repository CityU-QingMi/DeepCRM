    public ResolutionGroup retrieve( Artifact artifact, ArtifactRepository localRepository, List<ArtifactRepository> remoteRepositories )
        throws ArtifactMetadataRetrievalException
    {
        Set<Artifact> dependencies = new HashSet<>();

        if ( "g".equals( artifact.getArtifactId() ) )
        {
            Artifact a = null;
            try
            {
                a = factory.createBuildArtifact( "org.apache.maven", "h", "1.0", "jar" );
                dependencies.add( a );
            }
            catch ( Exception e )
            {
                throw new ArtifactMetadataRetrievalException( "Error retrieving metadata", e, a );
            }
        }

        if ( "i".equals( artifact.getArtifactId() ) )
        {
            Artifact a = null;
            try
            {
                a = factory.createBuildArtifact( "org.apache.maven", "j", "1.0-SNAPSHOT", "jar" );
                dependencies.add( a );
            }
            catch ( Exception e )
            {
                throw new ArtifactMetadataRetrievalException( "Error retrieving metadata", e, a );
            }
        }


        return new ResolutionGroup( artifact, dependencies, remoteRepositories );
    }
