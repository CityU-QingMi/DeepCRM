    public List<Path> getPaths(Path dir, int searchDepth, String pattern) throws IOException
    {
        if (PathMatchers.isAbsolute(pattern))
        {
            throw new RuntimeException("Pattern cannot be absolute: " + pattern);
        }

        List<Path> hits = new ArrayList<>();
        if (FS.isValidDirectory(dir))
        {
            PathMatcher matcher = PathMatchers.getMatcher(pattern);
            PathFinder finder = new PathFinder();
            finder.setFileMatcher(matcher);
            finder.setBase(dir);
            finder.setIncludeDirsInResults(true);
            Files.walkFileTree(dir,SEARCH_VISIT_OPTIONS,searchDepth,finder);
            hits.addAll(finder.getHits());
            Collections.sort(hits,new NaturalSort.Paths());
        }
        return hits;
    }
