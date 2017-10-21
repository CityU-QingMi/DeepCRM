    @Test
    public void testRemoveCompositeFilterFromCompositeFilter() {
        final Filter filter1 = ThresholdFilter.createFilter(Level.ERROR, null, null);
        final Filter filter2 = ThresholdFilter.createFilter(Level.ERROR, null, null);
        final Filter compositeFilter = CompositeFilter.createFilters(new Filter[]{filter1, filter2});

        filterable.addFilter(compositeFilter);
        filterable.removeFilter(compositeFilter);
        assertNull(filterable.getFilter());
    }
