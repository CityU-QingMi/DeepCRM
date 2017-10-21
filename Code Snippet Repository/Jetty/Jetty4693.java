    public void fix(Path buildRoot) throws IOException
    {
        // Find all of the *.mod files
        PathFinder finder = new PathFinder();
        finder.setFileMatcher("glob:**/*.mod");
        finder.setBase(buildRoot);

        // Matcher for target directories
        PathMatcher targetMatcher = PathMatchers.getMatcher("glob:**/target/**");
        PathMatcher testMatcher = PathMatchers.getMatcher("glob:**/test/**");

        System.out.printf("Walking path: %s%n",buildRoot);
        Set<FileVisitOption> options = Collections.emptySet();
        Files.walkFileTree(buildRoot,options,30,finder);

        System.out.printf("Found: %d hits%n",finder.getHits().size());
        int count = 0;
        for (Path path : finder.getHits())
        {
            if (Files.isDirectory(path))
            {
                // skip
                continue;
            }

            if (targetMatcher.matches(path))
            {
                // skip
                continue;
            }

            if (testMatcher.matches(path))
            {
                // skip
                continue;
            }

            if (processModFile(path))
            {
                count++;
            }
        }

        System.out.printf("Processed %,d modules",count);
    }
