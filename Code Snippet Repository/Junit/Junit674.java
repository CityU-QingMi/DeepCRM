	static <T> Filter<T> composeFilters(Collection<? extends Filter<T>> filters) {
		Preconditions.notNull(filters, "Filters must not be null");

		if (filters.isEmpty()) {
			return alwaysIncluded();
		}
		if (filters.size() == 1) {
			return getOnlyElement(filters);
		}
		return new CompositeFilter<>(filters);
	}
