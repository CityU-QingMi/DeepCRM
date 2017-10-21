	@Test
	public void nestedAnnotations() throws Exception {
		Filter filter = FilteredClass.class.getAnnotation(Filter.class);

		attributes.put("filter", filter);
		attributes.put("filters", new Filter[] {filter, filter});

		Filter retrievedFilter = attributes.getAnnotation("filter", Filter.class);
		assertThat(retrievedFilter, equalTo(filter));
		assertThat(retrievedFilter.pattern(), equalTo("foo"));

		Filter[] retrievedFilters = attributes.getAnnotationArray("filters", Filter.class);
		assertNotNull(retrievedFilters);
		assertEquals(2, retrievedFilters.length);
		assertThat(retrievedFilters[1].pattern(), equalTo("foo"));
	}
