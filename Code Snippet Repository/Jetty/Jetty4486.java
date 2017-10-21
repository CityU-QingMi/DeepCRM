    protected boolean isFilePresent(Path... paths) throws IOException
    {
        for (Path file : paths)
        {
            if (Files.exists(file))
            {
                if (Files.isDirectory(file))
                {
                    throw new IOException("Directory in the way: " + file.toAbsolutePath());
                }

                if (!Files.isReadable(file))
                {
                    throw new IOException("File not readable: " + file.toAbsolutePath());
                }

                return true;
            }
        }

        return false;
    }
