    private void mergeMetadata( Versions versions, Metadata source, ArtifactRepository repository )
    {
        Versioning versioning = source.getVersioning();
        if ( versioning != null )
        {
            String timestamp = StringUtils.clean( versioning.getLastUpdated() );

            if ( StringUtils.isNotEmpty( versioning.getRelease() )
                && timestamp.compareTo( versions.releaseTimestamp ) > 0 )
            {
                versions.releaseVersion = versioning.getRelease();
                versions.releaseTimestamp = timestamp;
                versions.releaseRepository = repository;
            }

            if ( StringUtils.isNotEmpty( versioning.getLatest() )
                && timestamp.compareTo( versions.latestTimestamp ) > 0 )
            {
                versions.latestVersion = versioning.getLatest();
                versions.latestTimestamp = timestamp;
                versions.latestRepository = repository;
            }

            for ( String version : versioning.getVersions() )
            {
                if ( !versions.versions.containsKey( version ) )
                {
                    versions.versions.put( version, repository );
                }
            }
        }
    }
