    public static void ensureDirectoryWritable(Path dir) throws IOException
    {
        if (!Files.exists(dir))
        {
            throw new IOException("Path does not exist: " + dir.toAbsolutePath());
        }
        if (!Files.isDirectory(dir))
        {
            throw new IOException("Directory does not exist: " + dir.toAbsolutePath());
        }
        if (!Files.isWritable(dir))
        {
            throw new IOException("Unable to write to directory: " + dir.toAbsolutePath());
        }
    }
