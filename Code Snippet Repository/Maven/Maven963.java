    private List<ArtifactVersion> retrieveAvailableVersionsFromMetadata( Metadata repoMetadata,
                                                                         List<String> availableVersions )
    {
        Collection<String> versions = new LinkedHashSet<>();

        if ( ( repoMetadata != null ) && ( repoMetadata.getVersioning() != null ) )
        {
            versions.addAll( repoMetadata.getVersioning().getVersions() );
        }

        versions.addAll( availableVersions );

        List<ArtifactVersion> artifactVersions = new ArrayList<>( versions.size() );

        for ( String version : versions )
        {
            artifactVersions.add( new DefaultArtifactVersion( version ) );
        }

        return artifactVersions;
    }
