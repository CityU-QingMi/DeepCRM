    private void copyDir(Path from, Path to, PathMatcher fileMatcher, Renamer renamer, FileCopier copier) throws IOException
    {
        Files.createDirectories(to);

        for (Path path : Files.newDirectoryStream(from))
        {
            String name = renamer.getName(path);
            Path dest = to.resolve(name);
            if (Files.isDirectory(path))
            {
                copyDir(path,dest,fileMatcher,renamer,copier);
            }
            else
            {
                if (fileMatcher.matches(path))
                {
                    copier.copy(path,dest);
                }
            }
        }
    }
