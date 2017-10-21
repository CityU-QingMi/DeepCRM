    public Iterator<Filter> getFilters() {
        final Filter filter = privateConfig.loggerConfig.getFilter();
        if (filter == null) {
            return new ArrayList<Filter>().iterator();
        } else if (filter instanceof CompositeFilter) {
            return ((CompositeFilter) filter).iterator();
        } else {
            final List<Filter> filters = new ArrayList<>();
            filters.add(filter);
            return filters.iterator();
        }
    }
