	public static Set<FilterKey> createFilterKeys(Map<String,Filter> enabledFilters) {
		if ( enabledFilters.size() == 0 ) {
			return null;
		}
		final Set<FilterKey> result = new HashSet<FilterKey>();
		for ( Filter filter : enabledFilters.values() ) {
			final FilterKey key = new FilterKey(
					filter.getName(),
					( (FilterImpl) filter ).getParameters(),
					filter.getFilterDefinition().getParameterTypes()
			);
			result.add( key );
		}
		return result;
	}
