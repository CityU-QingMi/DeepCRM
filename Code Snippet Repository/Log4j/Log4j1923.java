    @Test
    public void testRecentFirst() throws Exception {
        final SortingVisitor visitor = new SortingVisitor(new PathSortByModificationTime(true));
        final Set<FileVisitOption> options = Collections.emptySet();
        Files.walkFileTree(base, options, 1, visitor);

        final List<PathWithAttributes> found = visitor.getSortedPaths();
        assertNotNull(found);
        assertEquals("file count", 3, found.size());
        assertEquals("1st: most recent; sorted=" + found, ccc, found.get(0).getPath());
        assertEquals("2nd; sorted=" + found, bbb, found.get(1).getPath());
        assertEquals("3rd: oldest; sorted=" + found, aaa, found.get(2).getPath());
    }
