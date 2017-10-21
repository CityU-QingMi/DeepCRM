    public void storeInLocalRepository( ArtifactRepository localRepository, ArtifactRepository remoteRepository )
        throws RepositoryMetadataStoreException
    {
        File destination =
            new File( localRepository.getBasedir(), localRepository.pathOfLocalRepositoryMetadata( this,
                                                                                                   remoteRepository ) );

        // ----------------------------------------------------------------------------
        // I'm fully aware that the file could just be moved using File.rename but
        // there are bugs in various JVM that have problems doing this across
        // different filesystem. So we'll incur the small hit to actually copy
        // here and be safe. jvz.
        // ----------------------------------------------------------------------------

        try
        {
            FileUtils.copyFile( file, destination );
        }
        catch ( IOException e )
        {
            throw new RepositoryMetadataStoreException( "Error copying POM to the local repository.", e );
        }
    }
