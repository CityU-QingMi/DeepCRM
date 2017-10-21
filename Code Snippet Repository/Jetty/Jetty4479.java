    public static boolean isValidDirectory(Path path)
    {
        if (!Files.exists(path))
        {
            // doesn't exist, not a valid directory
            return false;
        }

        if (!Files.isDirectory(path))
        {
            // not a directory (as expected)
            StartLog.warn("Not a directory: " + path);
            return false;
        }

        return true;
    }
