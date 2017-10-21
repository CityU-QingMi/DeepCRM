    @Test
    public void testAcceptCallsNestedConditionsOnlyIfPathAccepted1() {
        final CountingCondition counter = new CountingCondition(true);
        final IfFileName regexFilter = IfFileName.createNameCondition(null, "regex", counter);
        final Path pathMatchingRegex = Paths.get("regex");
        
        assertTrue(regexFilter.accept(null, pathMatchingRegex, null));
        assertEquals(1, counter.getAcceptCount());
        assertTrue(regexFilter.accept(null, pathMatchingRegex, null));
        assertEquals(2, counter.getAcceptCount());
        assertTrue(regexFilter.accept(null, pathMatchingRegex, null));
        assertEquals(3, counter.getAcceptCount());
        
        final Path noMatch = Paths.get("nomatch");
        assertFalse(regexFilter.accept(null, noMatch, null));
        assertEquals(3, counter.getAcceptCount()); // no increase
        assertFalse(regexFilter.accept(null, noMatch, null));
        assertEquals(3, counter.getAcceptCount());
        assertFalse(regexFilter.accept(null, noMatch, null));
        assertEquals(3, counter.getAcceptCount());
    }
