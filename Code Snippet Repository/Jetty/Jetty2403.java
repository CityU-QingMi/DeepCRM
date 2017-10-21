    private Path prepareTargetTestsDir() throws IOException
    {
        final Path targetTestsDir = MavenTestingUtils.getTargetTestingDir().toPath();
        Files.createDirectories(targetTestsDir);
        try (DirectoryStream<Path> files = Files.newDirectoryStream(targetTestsDir, "*.*"))
        {
            for (Path file : files)
            {
                if (!Files.isDirectory(file))
                    Files.delete(file);
            }
        }
        return targetTestsDir;
    }
