    private void merge( String key, Map<String, VersionInfo> infos, String timestamp, String version,
                        ArtifactRepository repository )
    {
        VersionInfo info = infos.get( key );
        if ( info == null )
        {
            info = new VersionInfo( timestamp, version, repository );
            infos.put( key, info );
        }
        else if ( info.isOutdated( timestamp ) )
        {
            info.version = version;
            info.repository = repository;
            info.timestamp = timestamp;
        }
    }
