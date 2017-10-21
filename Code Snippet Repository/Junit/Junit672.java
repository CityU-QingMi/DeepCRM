	@Override
	public FilterResult apply(T element) {
		// @formatter:off
		return filters.stream()
				.map(filter -> filter.apply(element))
				.filter(FilterResult::excluded)
				.findFirst()
				.orElse(INCLUDED_BY_ALL_FILTERS);
		// @formatter:on
	}
