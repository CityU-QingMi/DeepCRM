    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException
    {
        if (dirMatcher.matches(dir))
        {
            StartLog.trace("Following dir: " + dir);
            if (includeDirsInResults && fileMatcher.matches(dir))
            {
                addHit(dir);
            }
            return FileVisitResult.CONTINUE;
        }
        else
        {
            StartLog.trace("Skipping dir: " + dir);
            return FileVisitResult.SKIP_SUBTREE;
        }
    }
