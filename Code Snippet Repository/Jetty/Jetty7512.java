    public static String toSystemPath(String rawpath)
    {
        Path path = FileSystems.getDefault().getPath(rawpath);
        if (Files.exists(path))
        {
            // It exists, resolve it to the real path
            try
            {
                path = path.toRealPath();
            }
            catch (IOException e)
            {
                // something prevented us from resolving to real path, fallback to
                // absolute path resolution (not as accurate)
                path = path.toAbsolutePath();
                e.printStackTrace();
            }
        }
        else
        {
            // File doesn't exist, resolve to absolute path
            // We can't rely on File.toCanonicalPath() here
            path = path.toAbsolutePath();
        }
        return path.toString();
    }
