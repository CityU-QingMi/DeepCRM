    public void retrieve( ArtifactRepository repository, File destination, String remotePath,
                          ArtifactTransferListener transferListener )
        throws ArtifactTransferFailedException, ArtifactDoesNotExistException
    {
        try
        {
            wagonManager.getRemoteFile( repository, destination, remotePath,
                                        TransferListenerAdapter.newAdapter( transferListener ),
                                        ArtifactRepositoryPolicy.CHECKSUM_POLICY_WARN, true );
        }
        catch ( org.apache.maven.wagon.TransferFailedException e )
        {
            throw new ArtifactTransferFailedException( getMessage( e, "Error transferring artifact." ), e );
        }
        catch ( org.apache.maven.wagon.ResourceDoesNotExistException e )
        {
            throw new ArtifactDoesNotExistException( getMessage( e, "Requested artifact does not exist." ), e );
        }
    }
