    @Override
    public boolean create(URI uri, String location) throws IOException
    {
        Path destination = getDestination(uri,location);
        
        if (destination == null)
        {
            StartLog.error("Bad file arg %s",uri);
            return false;
        }
            
        boolean isDir = location.endsWith("/");

        if (FS.exists(destination))
        {
            // Validate existence
            if (isDir)
            {
                if (!Files.isDirectory(destination))
                {
                    throw new IOException("Invalid: path should be a directory (but isn't): " + location);
                }
                if (!FS.canReadDirectory(destination))
                {
                    throw new IOException("Unable to read directory: " + location);
                }
            }
            else
            {
                if (!FS.canReadFile(destination))
                {
                    throw new IOException("Unable to read file: " + location);
                }
            }

            return false;
        }

        if (isDir)
        {
            // Create directory
            boolean mkdir = FS.ensureDirectoryExists(destination);
            if (mkdir)
                StartLog.log("MKDIR",_basehome.toShortForm(destination));
            return mkdir;
        }
        
        throw new IOException("Unable to create "+_basehome.toShortForm(destination));
    }
