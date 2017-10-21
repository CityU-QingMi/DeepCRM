    public void merge( File current, File result )
        throws RepositoryException
    {
        try
        {
            if ( current.exists() )
            {
                FileUtils.copyFile( current, result );
            }
            ArtifactRepository localRepo = new MetadataRepository( result );
            metadata.storeInLocalRepository( localRepo, localRepo );
            merged = true;
        }
        catch ( Exception e )
        {
            throw new RepositoryException( e.getMessage(), e );
        }
    }
