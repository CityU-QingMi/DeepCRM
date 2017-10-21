    public CompositeFilter addFilter(final Filter filter) {
        if (filter == null) {
            // null does nothing
            return this;
        }
        if (filter instanceof CompositeFilter) {
            final int size = this.filters.length + ((CompositeFilter) filter).size();
            final Filter[] copy = Arrays.copyOf(this.filters, size);
            final int index = this.filters.length;
            for (final Filter currentFilter : ((CompositeFilter) filter).filters) {
                copy[index] = currentFilter;
            }
            return new CompositeFilter(copy);
        }
        final Filter[] copy = Arrays.copyOf(this.filters, this.filters.length + 1);
        copy[this.filters.length] = filter;
        return new CompositeFilter(copy);
    }
