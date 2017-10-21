    public boolean check(URI uri, String location) throws IOException
    {
        if (location!=null)
        {
            // Process directly
            boolean isDir = location.endsWith("/");
            Path destination = getDestination(uri,location);

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
                return true;
            }

            StartLog.error("Missing Required File: %s",_basehome.toShortForm(location));
            return false;
        }

        return true;
    }
