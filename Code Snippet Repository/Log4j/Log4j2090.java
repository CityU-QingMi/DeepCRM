    @Test
    public void testRemoveFiltersFromComposite() {
        final Filter filter1 = ThresholdFilter.createFilter(Level.ERROR, null, null);
        final Filter filter2 = ThresholdFilter.createFilter(Level.ERROR, null, null);
        final Filter compositeFilter = CompositeFilter.createFilters(new Filter[]{filter1, filter2});
        final Filter anotherFilter = ThresholdFilter.createFilter(Level.WARN, null, null);

        filterable.addFilter(compositeFilter);
        filterable.addFilter(anotherFilter);
        assertEquals(3, ((CompositeFilter) filterable.getFilter()).getFilters().size());
        filterable.removeFilter(filter1);
        assertEquals(2, ((CompositeFilter) filterable.getFilter()).getFilters().size());
        filterable.removeFilter(filter2);
        assertSame(anotherFilter, filterable.getFilter());
    }
