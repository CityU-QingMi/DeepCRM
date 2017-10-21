    public RemoteSnapshotMetadataGenerator( RepositorySystemSession session, DeployRequest request )
    {
        legacyFormat = ConfigUtils.getBoolean( session.getConfigProperties(), false, "maven.metadata.legacy" );

        snapshots = new LinkedHashMap<>();

/**/
/**/
/**/
/**/
/**/
/**/
        for ( Metadata metadata : request.getMetadata() )
        {
            if ( metadata instanceof RemoteSnapshotMetadata )
            {
                RemoteSnapshotMetadata snapshotMetadata = (RemoteSnapshotMetadata) metadata;
                snapshots.put( snapshotMetadata.getKey(), snapshotMetadata );
            }
        }
    }
