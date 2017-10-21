    public void publish( ArtifactRepository repository, File source, String remotePath,
                         ArtifactTransferListener transferListener )
        throws ArtifactTransferFailedException
    {
        try
        {
            wagonManager.putRemoteFile( repository, source, remotePath,
                                        TransferListenerAdapter.newAdapter( transferListener ) );
        }
        catch ( org.apache.maven.wagon.TransferFailedException e )
        {
            throw new ArtifactTransferFailedException( getMessage( e, "Error transferring artifact." ), e );
        }
    }
