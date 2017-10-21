    @Override
    public boolean create(URI uri, String location) throws IOException
    {
        Path destination = getDestination(uri,location);
        if (destination!=null)
        {
            if (location.endsWith("/"))
                FS.ensureDirectoryExists(destination);
            else
                FS.ensureDirectoryExists(destination.getParent());
        }
        
        StartLog.log("TESTING MODE","Skipping download of " + uri);
        return Boolean.TRUE;
    }
