    private void mergeMetadata( RepositorySystemSession session, RequestTrace trace, Versions versions,
                                org.eclipse.aether.metadata.Metadata metadata, ArtifactRepository repository )
    {
        if ( metadata != null && metadata.getFile() != null && metadata.getFile().isFile() )
        {
            try
            {
                Map<String, ?> options = Collections.singletonMap( MetadataReader.IS_STRICT, Boolean.FALSE );

                Metadata repoMetadata = metadataReader.read( metadata.getFile(), options );

                mergeMetadata( versions, repoMetadata, repository );
            }
            catch ( IOException e )
            {
                invalidMetadata( session, trace, metadata, repository, e );
            }
        }
    }
