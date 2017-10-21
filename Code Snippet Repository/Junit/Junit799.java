	@Test
	void resolvesClassForMethodSelectorForClassWithNonFilterableRunner() throws Exception {
		Class<?> testClass = IgnoredJUnit4TestCase.class;
		// @formatter:off
		LauncherDiscoveryRequest request = request()
				.selectors(selectMethod(testClass, testClass.getMethod("test")))
				.build();
		// @formatter:on

		TestDescriptor engineDescriptor = discoverTests(request);

		TestDescriptor runnerDescriptor = getOnlyElement(engineDescriptor.getChildren());
		assertEquals(testClass.getName(), runnerDescriptor.getDisplayName());
		Assertions.assertEquals(VintageUniqueIdBuilder.uniqueIdForClass(testClass), runnerDescriptor.getUniqueId());
		assertThat(runnerDescriptor.getChildren()).isEmpty();
	}
