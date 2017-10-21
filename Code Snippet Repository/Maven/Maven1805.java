    private void merge( Artifact artifact, Map<String, VersionInfo> infos, Versioning versioning,
                        ArtifactRepository repository )
    {
        if ( StringUtils.isNotEmpty( versioning.getRelease() ) )
        {
            merge( RELEASE, infos, versioning.getLastUpdated(), versioning.getRelease(), repository );
        }

        if ( StringUtils.isNotEmpty( versioning.getLatest() ) )
        {
            merge( LATEST, infos, versioning.getLastUpdated(), versioning.getLatest(), repository );
        }

        for ( SnapshotVersion sv : versioning.getSnapshotVersions() )
        {
            if ( StringUtils.isNotEmpty( sv.getVersion() ) )
            {
                String key = getKey( sv.getClassifier(), sv.getExtension() );
                merge( SNAPSHOT + key, infos, sv.getUpdated(), sv.getVersion(), repository );
            }
        }

        Snapshot snapshot = versioning.getSnapshot();
        if ( snapshot != null && versioning.getSnapshotVersions().isEmpty() )
        {
            String version = artifact.getVersion();
            if ( snapshot.getTimestamp() != null && snapshot.getBuildNumber() > 0 )
            {
                String qualifier = snapshot.getTimestamp() + '-' + snapshot.getBuildNumber();
                version = version.substring( 0, version.length() - SNAPSHOT.length() ) + qualifier;
            }
            merge( SNAPSHOT, infos, versioning.getLastUpdated(), version, repository );
        }
    }
