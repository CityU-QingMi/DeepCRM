    public List<ArtifactRepository> createArtifactRepositories( List<Repository> pomRepositories,
                                                                List<ArtifactRepository> externalRepositories,
                                                                ProjectBuildingRequest request )
    {
        if ( externalRepositories != null )
        {
            return externalRepositories;
        }
        else
        {
            return new ArrayList<>();
        }
    }
