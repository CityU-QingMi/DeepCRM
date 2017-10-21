    private void updateSnapshotMetadata( RepositoryMetadata metadata,
                                         Map<ArtifactRepository, Metadata> previousMetadata,
                                         ArtifactRepository selected, ArtifactRepository localRepository )
        throws RepositoryMetadataStoreException
    {
        // TODO this could be a lot nicer... should really be in the snapshot transformation?
        if ( metadata.isSnapshot() )
        {
            Metadata prevMetadata = metadata.getMetadata();

            for ( ArtifactRepository repository : previousMetadata.keySet() )
            {
                Metadata m = previousMetadata.get( repository );
                if ( repository.equals( selected ) )
                {
                    if ( m.getVersioning() == null )
                    {
                        m.setVersioning( new Versioning() );
                    }

                    if ( m.getVersioning().getSnapshot() == null )
                    {
                        m.getVersioning().setSnapshot( new Snapshot() );
                    }
                }
                else
                {
                    if ( ( m.getVersioning() != null ) && ( m.getVersioning().getSnapshot() != null )
                        && m.getVersioning().getSnapshot().isLocalCopy() )
                    {
                        m.getVersioning().getSnapshot().setLocalCopy( false );
                        metadata.setMetadata( m );
                        metadata.storeInLocalRepository( localRepository, repository );
                    }
                }
            }

            metadata.setMetadata( prevMetadata );
        }
    }
