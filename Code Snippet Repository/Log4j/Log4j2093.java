    @Test
    public void testAddMultipleCompositeFilters() throws Exception {
        final Filter filter1 = ThresholdFilter.createFilter(Level.ERROR, null, null);
        final Filter filter2 = ThresholdFilter.createFilter(Level.ERROR, null, null);
        final Filter filter3 = ThresholdFilter.createFilter(Level.ERROR, null, null);
        final Filter compositeFilter = CompositeFilter.createFilters(new Filter[]{filter1, filter2, filter3});

        filterable.addFilter(compositeFilter);
        assertSame(compositeFilter, filterable.getFilter());
        // adding a second filter converts the filter
        // into a CompositeFilter.class
        filterable.addFilter(compositeFilter);
        assertTrue(filterable.getFilter() instanceof CompositeFilter);
        assertEquals(6, ((CompositeFilter) filterable.getFilter()).getFilters().size());
    }
