    public void resolveAlways( RepositoryMetadata metadata, ArtifactRepository localRepository,
                               ArtifactRepository remoteRepository )
        throws RepositoryMetadataResolutionException
    {
        File file;
        try
        {
            file = getArtifactMetadataFromDeploymentRepository( metadata, localRepository, remoteRepository );
        }
        catch ( TransferFailedException e )
        {
            throw new RepositoryMetadataResolutionException(
                metadata + " could not be retrieved from repository: " + remoteRepository.getId() + " due to an error: "
                    + e.getMessage(), e );
        }

        try
        {
            if ( file.exists() )
            {
                Metadata prevMetadata = readMetadata( file );
                metadata.setMetadata( prevMetadata );
            }
        }
        catch ( RepositoryMetadataReadException e )
        {
            throw new RepositoryMetadataResolutionException( e.getMessage(), e );
        }
    }
