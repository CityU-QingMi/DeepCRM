    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException
    {
        if (exc instanceof FileSystemLoopException)
        {
            if (!NOTIFIED_PATHS.contains(file))
            {
                StartLog.warn("skipping detected filesystem loop: " + file);
                NOTIFIED_PATHS.add(file);
            }
            return FileVisitResult.SKIP_SUBTREE;
        }
        else
        {
            StartLog.warn(exc);
            return super.visitFileFailed(file,exc);
        }
    }
