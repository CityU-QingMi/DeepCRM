    @Test
    public void testRemoveSimpleFilterFromCompositeAndSimpleFilter() {
        final Filter filter1 = ThresholdFilter.createFilter(Level.ERROR, null, null);
        final Filter filter2 = ThresholdFilter.createFilter(Level.ERROR, null, null);
        final Filter compositeFilter = CompositeFilter.createFilters(new Filter[]{filter1, filter2});
        final Filter anotherFilter = ThresholdFilter.createFilter(Level.WARN, null, null);


        filterable.addFilter(compositeFilter);
        filterable.addFilter(anotherFilter);

        // should not remove internal filter of compositeFilter
        filterable.removeFilter(anotherFilter);
        assertTrue(filterable.getFilter() instanceof CompositeFilter);
        assertEquals(2, ((CompositeFilter) filterable.getFilter()).getFilters().size());
    }
