    private Versioning readVersions( RepositorySystemSession session, RequestTrace trace, Metadata metadata,
                                     ArtifactRepository repository, VersionResult result )
    {
        Versioning versioning = null;
        try
        {
            if ( metadata != null )
            {
                try ( SyncContext syncContext = syncContextFactory.newInstance( session, true ) )
                {
                    syncContext.acquire( null, Collections.singleton( metadata ) );

                    if ( metadata.getFile() != null && metadata.getFile().exists() )
                    {
                        try ( final InputStream in = new FileInputStream( metadata.getFile() ) )
                        {
                            versioning = new MetadataXpp3Reader().read( in, false ).getVersioning();

/**/
/**/
/**/
/**/
/**/
                            if ( versioning != null && repository instanceof LocalRepository
                                     && versioning.getSnapshot() != null
                                     && versioning.getSnapshot().getBuildNumber() > 0 )
                            {
                                final Versioning repaired = new Versioning();
                                repaired.setLastUpdated( versioning.getLastUpdated() );
                                repaired.setSnapshot( new Snapshot() );
                                repaired.getSnapshot().setLocalCopy( true );
                                versioning = repaired;
                                throw new IOException( "Snapshot information corrupted with remote repository data"
                                                           + ", please verify that no remote repository uses the id '"
                                                           + repository.getId() + "'" );

                            }
                        }
                    }
                }
            }
        }
        catch ( Exception e )
        {
            invalidMetadata( session, trace, metadata, repository, e );
            result.addException( e );
        }

        return ( versioning != null ) ? versioning : new Versioning();
    }
