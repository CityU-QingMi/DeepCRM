    public List<ArtifactVersion> retrieveAvailableVersionsFromDeploymentRepository( Artifact artifact,
                                                                                    ArtifactRepository localRepository,
                                                                              ArtifactRepository deploymentRepository )
        throws ArtifactMetadataRetrievalException
    {
        RepositoryMetadata metadata = new ArtifactRepositoryMetadata( artifact );

        try
        {
            repositoryMetadataManager.resolveAlways( metadata, localRepository, deploymentRepository );
        }
        catch ( RepositoryMetadataResolutionException e )
        {
            throw new ArtifactMetadataRetrievalException( e.getMessage(), e, artifact );
        }

        List<String> availableVersions = localRepository.findVersions( artifact );

        return retrieveAvailableVersionsFromMetadata( metadata.getMetadata(), availableVersions );
    }
