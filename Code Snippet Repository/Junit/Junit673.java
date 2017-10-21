	@SafeVarargs
	@SuppressWarnings("")
	static <T> Filter<T> composeFilters(Filter<T>... filters) {
		Preconditions.notNull(filters, "filters array must not be null");
		Preconditions.containsNoNullElements(filters, "individual filters must not be null");

		if (filters.length == 0) {
			return alwaysIncluded();
		}
		if (filters.length == 1) {
			return filters[0];
		}
		return new CompositeFilter<>(asList(filters));
	}
