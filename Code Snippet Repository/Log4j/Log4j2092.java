    @Test
    public void testAddMultipleEqualSimpleFilter() throws Exception {
        final Filter filter = new EqualFilter("test");

        filterable.addFilter(filter);
        assertSame(filter, filterable.getFilter());
        // adding a second filter converts the filter
        // into a CompositeFilter.class
        filterable.addFilter(filter);
        assertTrue(filterable.getFilter() instanceof CompositeFilter);
        assertEquals(2, ((CompositeFilter) filterable.getFilter()).getFilters().size());
    }
