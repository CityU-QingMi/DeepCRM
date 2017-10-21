	@Test
	public void synthesizeAnnotationWithAttributeAliasesInNestedAnnotations() throws Exception {
		List<String> expectedLocations = asList("A", "B");

		Hierarchy hierarchy = ConfigHierarchyTestCase.class.getAnnotation(Hierarchy.class);
		assertNotNull(hierarchy);
		Hierarchy synthesizedHierarchy = synthesizeAnnotation(hierarchy);
		assertNotSame(hierarchy, synthesizedHierarchy);
		assertThat(synthesizedHierarchy, instanceOf(SynthesizedAnnotation.class));

		ContextConfig[] configs = synthesizedHierarchy.value();
		assertNotNull(configs);
		assertTrue("nested annotations must be synthesized",
				stream(configs).allMatch(c -> c instanceof SynthesizedAnnotation));

		List<String> locations = stream(configs).map(ContextConfig::location).collect(toList());
		assertThat(locations, is(expectedLocations));

		List<String> values = stream(configs).map(ContextConfig::value).collect(toList());
		assertThat(values, is(expectedLocations));
	}
