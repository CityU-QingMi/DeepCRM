	private Filter<?>[] getIncludeAndExcludeFilters() {
		List<Filter<?>> filters = new ArrayList<>();

		Optional<List<String>> includes = getGroupsOrTags(getPropertiesList(INCLUDE_GROUPS),
			getPropertiesList(INCLUDE_TAGS));
		includes.map(TagFilter::includeTags).ifPresent(filters::add);

		Optional<List<String>> excludes = getGroupsOrTags(getPropertiesList(EXCLUDE_GROUPS),
			getPropertiesList(EXCLUDE_TAGS));
		excludes.map(TagFilter::excludeTags).ifPresent(filters::add);

		return filters.toArray(new Filter<?>[filters.size()]);
	}
