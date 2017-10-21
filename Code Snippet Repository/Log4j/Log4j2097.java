    @Test
    public void testRemoveSimpleEqualFilterFromMultipleSimpleFilters() throws Exception {
        final Filter filterOriginal = new EqualFilter("test");
        final Filter filterCopy = new EqualFilter("test");

        filterable.addFilter(filterOriginal);
        filterable.addFilter(filterOriginal);
        filterable.addFilter(filterCopy);
        filterable.removeFilter(filterCopy);
        assertTrue(filterable.getFilter() instanceof CompositeFilter);
        assertEquals(2, ((CompositeFilter) filterable.getFilter()).getFilters().size());
        filterable.removeFilter(filterCopy);
        assertEquals(filterOriginal, filterable.getFilter());
        filterable.removeFilter(filterOriginal);
        assertNull(filterable.getFilter());
    }
