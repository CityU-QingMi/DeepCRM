    private void mergeMetadata( RepositoryMetadata metadata, List<ArtifactRepository> remoteRepositories,
                                ArtifactRepository localRepository )
        throws RepositoryMetadataStoreException
    {
        // TODO currently this is first wins, but really we should take the latest by comparing either the
        // snapshot timestamp, or some other timestamp later encoded into the metadata.
        // TODO this needs to be repeated here so the merging doesn't interfere with the written metadata
        //  - we'd be much better having a pristine input, and an ongoing metadata for merging instead

        Map<ArtifactRepository, Metadata> previousMetadata = new HashMap<>();
        ArtifactRepository selected = null;
        for ( ArtifactRepository repository : remoteRepositories )
        {
            ArtifactRepositoryPolicy policy = metadata.getPolicy( repository );

            if ( policy.isEnabled() && loadMetadata( metadata, repository, localRepository, previousMetadata ) )
            {
                metadata.setRepository( repository );
                selected = repository;
            }
        }
        if ( loadMetadata( metadata, localRepository, localRepository, previousMetadata ) )
        {
            metadata.setRepository( null );
            selected = localRepository;
        }

        updateSnapshotMetadata( metadata, previousMetadata, selected, localRepository );
    }
