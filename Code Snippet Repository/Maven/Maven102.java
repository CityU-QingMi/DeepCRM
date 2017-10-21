    private boolean loadMetadata( RepositoryMetadata repoMetadata, ArtifactRepository remoteRepository,
                                  ArtifactRepository localRepository,
                                  Map<ArtifactRepository, Metadata> previousMetadata )
    {
        boolean setRepository = false;

        File metadataFile = new File( localRepository.getBasedir(),
                                      localRepository.pathOfLocalRepositoryMetadata( repoMetadata, remoteRepository ) );

        if ( metadataFile.exists() )
        {
            Metadata metadata;

            try
            {
                metadata = readMetadata( metadataFile );
            }
            catch ( RepositoryMetadataReadException e )
            {
                if ( getLogger().isDebugEnabled() )
                {
                    getLogger().warn( e.getMessage(), e );
                }
                else
                {
                    getLogger().warn( e.getMessage() );
                }
                return setRepository;
            }

            if ( repoMetadata.isSnapshot() && ( previousMetadata != null ) )
            {
                previousMetadata.put( remoteRepository, metadata );
            }

            if ( repoMetadata.getMetadata() != null )
            {
                setRepository = repoMetadata.getMetadata().merge( metadata );
            }
            else
            {
                repoMetadata.setMetadata( metadata );
                setRepository = true;
            }
        }
        return setRepository;
    }
