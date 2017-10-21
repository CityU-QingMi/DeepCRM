    @Test
    public void testRemoveSimpleEqualFilterFromTwoSimpleFilters() throws Exception {
        final Filter filterOriginal = new EqualFilter("test");
        final Filter filterCopy = new EqualFilter("test");

        filterable.addFilter(filterOriginal);
        filterable.addFilter(filterOriginal);
        filterable.removeFilter(filterCopy);
        assertSame(filterOriginal, filterable.getFilter());
        filterable.removeFilter(filterCopy);
        assertNull(filterable.getFilter());
    }
