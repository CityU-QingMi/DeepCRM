    private static Path toCanonicalPath(Path path)
    {
        if (path == null)
        {
            return null;
        }
        if (Files.exists(path))
        {
            try
            {
                return path.toRealPath();
            }
            catch (IOException e)
            {
                throw new IllegalArgumentException(e);
            }
        }
        return path.toAbsolutePath();
    }
