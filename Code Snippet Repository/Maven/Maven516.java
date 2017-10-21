    public void storeInLocalRepository( ArtifactRepository localRepository, ArtifactRepository remoteRepository )
        throws RepositoryMetadataStoreException
    {
        try
        {
            updateRepositoryMetadata( localRepository, remoteRepository );
        }
        catch ( IOException | XmlPullParserException e )
        {
            throw new RepositoryMetadataStoreException( "Error updating group repository metadata", e );
        }
    }
