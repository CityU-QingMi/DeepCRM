	@Test
	public void getRepeatableAnnotationsDeclaredOnClassWithAttributeAliases() throws Exception {
		final List<String> expectedLocations = asList("A", "B");

		Set<ContextConfig> annotations = getRepeatableAnnotations(ConfigHierarchyTestCase.class, ContextConfig.class, null);
		assertNotNull(annotations);
		assertEquals("size if container type is omitted: ", 0, annotations.size());

		annotations = getRepeatableAnnotations(ConfigHierarchyTestCase.class, ContextConfig.class, Hierarchy.class);
		assertNotNull(annotations);

		List<String> locations = annotations.stream().map(ContextConfig::location).collect(toList());
		assertThat(locations, is(expectedLocations));

		List<String> values = annotations.stream().map(ContextConfig::value).collect(toList());
		assertThat(values, is(expectedLocations));
	}
