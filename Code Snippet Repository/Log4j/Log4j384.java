    @Test
    public void testDuplicateParents() {
        final Marker parent = MarkerManager.getMarker("PARENT");
        final Marker existing = MarkerManager.getMarker("EXISTING");
        final Marker test1 = MarkerManager.getMarker("TEST1").setParents(existing);
        test1.addParents(parent);
        final Marker[] parents = test1.getParents();
        test1.addParents(existing);
        assertTrue("duplicate add allowed", parents.length == test1.getParents().length);
        test1.addParents(existing, MarkerManager.getMarker("EXTRA"));
        assertTrue("incorrect add", parents.length + 1 == test1.getParents().length);
        assertTrue("TEST1 is not an instance of PARENT", test1.isInstanceOf(parent));
        assertTrue("TEST1 is not an instance of EXISTING", test1.isInstanceOf(existing));
    }
