    @Test
    public void testAddMultipleSimpleFilters() throws Exception {
        final Filter filter = ThresholdFilter.createFilter(Level.ERROR, null, null);

        filterable.addFilter(filter);
        assertSame(filter, filterable.getFilter());
        // adding a second filter converts the filter
        // into a CompositeFilter.class
        filterable.addFilter(filter);
        assertTrue(filterable.getFilter() instanceof CompositeFilter);
        assertEquals(2, ((CompositeFilter) filterable.getFilter()).getFilters().size());
    }
