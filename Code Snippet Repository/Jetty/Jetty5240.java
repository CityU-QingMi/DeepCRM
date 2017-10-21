    @Override
    public File getFile()
        throws IOException
    {
        // Try the permission hack
        if (checkConnection())
        {
            Permission perm = _connection.getPermission();
            if (perm instanceof java.io.FilePermission)
                return new File(perm.getName());
        }

        // Don't know the file
        return null;    
    }
