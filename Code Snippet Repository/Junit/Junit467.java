	@Test
	void discoverMultipleMethodsOfSameClass() throws NoSuchMethodException {
		LauncherDiscoveryRequest request = request().selectors(selectMethod(LocalTestCase.class, "test1"),
			selectMethod(LocalTestCase.class, "test2")).build();

		TestDescriptor engineDescriptor = discoverTests(request);

		assertThat(engineDescriptor.getChildren()).hasSize(1);
		TestDescriptor classDescriptor = getOnlyElement(engineDescriptor.getChildren());
		assertThat(classDescriptor.getChildren()).hasSize(2);
	}
