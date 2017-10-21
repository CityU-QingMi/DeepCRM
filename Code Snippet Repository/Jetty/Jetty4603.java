    @Override
    public boolean create(URI uri, String location) throws IOException
    {
        Coordinates coords = getCoordinates(uri);
        if (coords == null)
        {
            // Skip, not a maven:// URI
            return false;
        }

        Path destination = getDestination(uri,location);
        
        if (isFilePresent(destination))
            return false;

        
        // If using local repository
        if (this.localRepositoryDir != null)
        {
            // Grab copy from local repository (download if needed to local
            // repository)
            Path localRepoFile = getLocalRepoFile(coords);
            
            if (localRepoFile!=null)
            {
                if (FS.ensureDirectoryExists(destination.getParent()))
                    StartLog.log("MKDIR",_basehome.toShortForm(destination.getParent()));
                StartLog.log("COPY ","%s to %s",localRepoFile,_basehome.toShortForm(destination));
                Files.copy(localRepoFile,destination);
                return true;
            }
        }

        // normal non-local repo version
        download(coords.toCentralURI(),destination);
        return true;
    }
