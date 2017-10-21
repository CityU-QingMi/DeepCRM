    public Artifact transformArtifact( Artifact artifact )
    {
        if ( artifact.isSnapshot() && artifact.getVersion().equals( artifact.getBaseVersion() ) )
        {
            Object key = RemoteSnapshotMetadata.getKey( artifact );
            RemoteSnapshotMetadata snapshotMetadata = snapshots.get( key );
            if ( snapshotMetadata != null )
            {
                artifact = artifact.setVersion( snapshotMetadata.getExpandedVersion( artifact ) );
            }
        }

        return artifact;
    }
