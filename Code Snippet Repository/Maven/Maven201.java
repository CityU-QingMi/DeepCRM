    public boolean isUpdateRequired( RepositoryMetadata metadata, ArtifactRepository repository, File file )
    {
        // Here, we need to determine which policy to use. Release updateInterval will be used when
        // the metadata refers to a release artifact or meta-version, and snapshot updateInterval will be used when
        // it refers to a snapshot artifact or meta-version.
        // NOTE: Release metadata includes version information about artifacts that have been released, to allow
        // meta-versions like RELEASE and LATEST to resolve, and also to allow retrieval of the range of valid, released
        // artifacts available.
        ArtifactRepositoryPolicy policy = metadata.getPolicy( repository );

        if ( !policy.isEnabled() )
        {
            if ( getLogger().isDebugEnabled() )
            {
                getLogger().debug(
                    "Skipping update check for " + metadata.getKey() + " (" + file + ") from " + repository.getId()
                        + " (" + repository.getUrl() + ")" );
            }

            return false;
        }

        if ( getLogger().isDebugEnabled() )
        {
            getLogger().debug(
                "Determining update check for " + metadata.getKey() + " (" + file + ") from " + repository.getId()
                    + " (" + repository.getUrl() + ")" );
        }

        if ( file == null )
        {
            // TODO throw something instead?
            return true;
        }

        Date lastCheckDate = readLastUpdated( metadata, repository, file );

        return ( lastCheckDate == null ) || policy.checkOutOfDate( lastCheckDate );
    }
