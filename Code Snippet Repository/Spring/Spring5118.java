	@Test
	public void synthesizeAnnotationWithArrayOfAnnotations() throws Exception {
		List<String> expectedLocations = asList("A", "B");

		Hierarchy hierarchy = ConfigHierarchyTestCase.class.getAnnotation(Hierarchy.class);
		assertNotNull(hierarchy);
		Hierarchy synthesizedHierarchy = synthesizeAnnotation(hierarchy);
		assertThat(synthesizedHierarchy, instanceOf(SynthesizedAnnotation.class));

		ContextConfig contextConfig = SimpleConfigTestCase.class.getAnnotation(ContextConfig.class);
		assertNotNull(contextConfig);

		ContextConfig[] configs = synthesizedHierarchy.value();
		List<String> locations = stream(configs).map(ContextConfig::location).collect(toList());
		assertThat(locations, is(expectedLocations));

		// Alter array returned from synthesized annotation
		configs[0] = contextConfig;

		// Re-retrieve the array from the synthesized annotation
		configs = synthesizedHierarchy.value();
		List<String> values = stream(configs).map(ContextConfig::value).collect(toList());
		assertThat(values, is(expectedLocations));
	}
