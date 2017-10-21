    @Test
    public void testRemoveSimpleFilterFromCompositeFilter() {
        final Filter filter1 = ThresholdFilter.createFilter(Level.ERROR, null, null);
        final Filter filter2 = ThresholdFilter.createFilter(Level.ERROR, null, null);
        final Filter compositeFilter = CompositeFilter.createFilters(new Filter[]{filter1, filter2});

        filterable.addFilter(compositeFilter);

        // should remove internal filter of compositeFilter
        filterable.removeFilter(filter1);
        assertFalse(filterable.getFilter() instanceof CompositeFilter);

        assertEquals(filter2, filterable.getFilter());
    }
