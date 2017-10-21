    public boolean isUpdateRequired( Artifact artifact, ArtifactRepository repository )
    {
        File file = artifact.getFile();

        ArtifactRepositoryPolicy policy = artifact.isSnapshot() ? repository.getSnapshots() : repository.getReleases();

        if ( !policy.isEnabled() )
        {
            if ( getLogger().isDebugEnabled() )
            {
                getLogger().debug(
                    "Skipping update check for " + artifact + " (" + file + ") from " + repository.getId() + " ("
                        + repository.getUrl() + ")" );
            }

            return false;
        }

        if ( getLogger().isDebugEnabled() )
        {
            getLogger().debug(
                "Determining update check for " + artifact + " (" + file + ") from " + repository.getId() + " ("
                    + repository.getUrl() + ")" );
        }

        if ( file == null )
        {
            // TODO throw something instead?
            return true;
        }

        Date lastCheckDate;

        if ( file.exists() )
        {
            lastCheckDate = new Date( file.lastModified() );
        }
        else
        {
            File touchfile = getTouchfile( artifact );
            lastCheckDate = readLastUpdated( touchfile, getRepositoryKey( repository ) );
        }

        return ( lastCheckDate == null ) || policy.checkOutOfDate( lastCheckDate );
    }
