    @Test
    public void testAddCompositeFilterAndSimpleFilter() throws Exception {
        final Filter filter1 = ThresholdFilter.createFilter(Level.ERROR, null, null);
        final Filter filter2 = ThresholdFilter.createFilter(Level.ERROR, null, null);
        final Filter notInCompositeFilterFilter = ThresholdFilter.createFilter(Level.ERROR, null, null);
        final Filter compositeFilter = CompositeFilter.createFilters(new Filter[]{filter1, filter2});

        filterable.addFilter(compositeFilter);
        assertSame(compositeFilter, filterable.getFilter());
        // adding a second filter converts the filter
        // into a CompositeFilter.class
        filterable.addFilter(notInCompositeFilterFilter);
        assertTrue(filterable.getFilter() instanceof CompositeFilter);
        assertEquals(3, ((CompositeFilter) filterable.getFilter()).getFilters().size());
    }
