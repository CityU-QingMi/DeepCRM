    public CompositeFilter removeFilter(final Filter filter) {
        if (filter == null) {
            // null does nothing
            return this;
        }
        // This is not a great implementation but simpler than copying Apache Commons
        // Lang ArrayUtils.removeElement() and associated bits (MutableInt),
        // which is OK since removing a filter should not be on the critical path.
        final List<Filter> filterList = new ArrayList<>(Arrays.asList(this.filters));
        if (filter instanceof CompositeFilter) {
            for (final Filter currentFilter : ((CompositeFilter) filter).filters) {
                filterList.remove(currentFilter);
            }
        } else {
            filterList.remove(filter);
        }
        return new CompositeFilter(filterList.toArray(new Filter[this.filters.length - 1]));
    }
