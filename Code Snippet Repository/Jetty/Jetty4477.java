    public static boolean ensureDirectoryExists(Path dir) throws IOException
    {
        if (exists(dir))
        {
            // Is it a directory?
            if (!Files.isDirectory(dir))
                throw new IOException("Path is not directory: " + dir.toAbsolutePath());
            
            // exists already, nothing to do
            return false;
        }
        Files.createDirectories(dir);
        return true;
    }
