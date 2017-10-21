    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
    {
        if (fileMatcher.matches(file))
        {
            addHit(file);
        }
        else
        {
            StartLog.trace("Ignoring file: " + file);
        }
        return FileVisitResult.CONTINUE;
    }
