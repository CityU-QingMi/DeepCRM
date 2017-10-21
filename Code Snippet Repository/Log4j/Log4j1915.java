    @Test
    public void testAcceptCallsNestedConditionsOnlyIfPathAccepted2() {
        final CountingCondition counter = new CountingCondition(true);
        final IfFileName globFilter = IfFileName.createNameCondition("glob", null, counter);
        final Path pathMatchingGlob = Paths.get("glob");
        
        assertTrue(globFilter.accept(null, pathMatchingGlob, null));
        assertEquals(1, counter.getAcceptCount());
        assertTrue(globFilter.accept(null, pathMatchingGlob, null));
        assertEquals(2, counter.getAcceptCount());
        assertTrue(globFilter.accept(null, pathMatchingGlob, null));
        assertEquals(3, counter.getAcceptCount());

        final Path noMatch = Paths.get("nomatch");
        assertFalse(globFilter.accept(null, noMatch, null));
        assertEquals(3, counter.getAcceptCount()); // no increase
        assertFalse(globFilter.accept(null, noMatch, null));
        assertEquals(3, counter.getAcceptCount());
        assertFalse(globFilter.accept(null, noMatch, null));
        assertEquals(3, counter.getAcceptCount());
    }
