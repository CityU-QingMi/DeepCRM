    public Collection<? extends Metadata> prepare( Collection<? extends Artifact> artifacts )
    {
        for ( Artifact artifact : artifacts )
        {
            if ( artifact.isSnapshot() )
            {
                Object key = LocalSnapshotMetadata.getKey( artifact );
                LocalSnapshotMetadata snapshotMetadata = snapshots.get( key );
                if ( snapshotMetadata == null )
                {
                    snapshotMetadata = new LocalSnapshotMetadata( artifact, legacyFormat );
                    snapshots.put( key, snapshotMetadata );
                }
                snapshotMetadata.bind( artifact );
            }
        }

        return Collections.emptyList();
    }
