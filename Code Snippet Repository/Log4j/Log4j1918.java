    @Test
    public void testCompareRecentLast() {
        final PathSorter sorter = PathSortByModificationTime.createSorter(false);
        final Path p1 = Paths.get("aaa");
        final Path p2 = Paths.get("bbb");
        final DummyFileAttributes a1 = new DummyFileAttributes();
        final DummyFileAttributes a2 = new DummyFileAttributes();
        a1.lastModified = FileTime.fromMillis(100);
        a2.lastModified = FileTime.fromMillis(222);
        
        assertEquals("same path, 2nd more recent", -1, sorter.compare(path(p1, a1), path(p1, a2)));
        assertEquals("path ignored, 2nd more recent", -1, sorter.compare(path(p1, a1), path(p2, a2)));
        assertEquals("path ignored, 2nd more recent", -1, sorter.compare(path(p2, a1), path(p1, a2)));
        
        assertEquals("same path, 1st more recent", 1, sorter.compare(path(p1, a2), path(p1, a1)));
        assertEquals("path ignored, 1st more recent", 1, sorter.compare(path(p1, a2), path(p2, a1)));
        assertEquals("path ignored, 1st more recent", 1, sorter.compare(path(p2, a2), path(p1, a1)));
        
        assertEquals("same path, same time", 0, sorter.compare(path(p1, a1), path(p1, a1)));
        assertEquals("p1 < p2, same time", -1, sorter.compare(path(p1, a1), path(p2, a1)));
        assertEquals("p1 < p2, same time", 1, sorter.compare(path(p2, a1), path(p1, a1)));
    }
