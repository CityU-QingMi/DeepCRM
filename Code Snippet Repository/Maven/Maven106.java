    private File getArtifactMetadataFromDeploymentRepository( ArtifactMetadata metadata, ArtifactRepository localRepo,
                                                              ArtifactRepository remoteRepository )
        throws TransferFailedException
    {
        File file =
            new File( localRepo.getBasedir(), localRepo.pathOfLocalRepositoryMetadata( metadata, remoteRepository ) );

        try
        {
            wagonManager.getArtifactMetadataFromDeploymentRepository( metadata, remoteRepository, file,
                                                                      ArtifactRepositoryPolicy.CHECKSUM_POLICY_WARN );
        }
        catch ( ResourceDoesNotExistException e )
        {
            getLogger().info(
                metadata + " could not be found on repository: " + remoteRepository.getId() + ", so will be created" );

            // delete the local copy so the old details aren't used.
            if ( file.exists() )
            {
                if ( !file.delete() )
                {
                    // sleep for 10ms just in case this is windows holding a file lock
                    try
                    {
                        Thread.sleep( 10 );
                    }
                    catch ( InterruptedException ie )
                    {
                        // ignore
                    }
                    file.delete(); // if this fails, forget about it
                }
            }
        }
        finally
        {
            if ( metadata instanceof RepositoryMetadata )
            {
                updateCheckManager.touch( (RepositoryMetadata) metadata, remoteRepository, file );
            }
        }
        return file;
    }
