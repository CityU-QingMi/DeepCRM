	@Test
	void usesCustomUniqueIdsWhenPresent() throws Exception {
		Class<?> testClass = JUnit4TestCaseWithRunnerWithCustomUniqueIds.class;
		LauncherDiscoveryRequest request = request().selectors(selectClass(testClass)).build();

		TestDescriptor engineDescriptor = discoverTests(request);

		TestDescriptor runnerDescriptor = getOnlyElement(engineDescriptor.getChildren());
		assertRunnerTestDescriptor(runnerDescriptor, testClass);

		TestDescriptor childDescriptor = getOnlyElement(runnerDescriptor.getChildren());

		UniqueId prefix = VintageUniqueIdBuilder.uniqueIdForClass(testClass);
		assertThat(childDescriptor.getUniqueId().toString()).startsWith(prefix.toString());

		String customUniqueIdValue = childDescriptor.getUniqueId().getSegments().get(2).getType();
		assertNotNull(Base64.getDecoder().decode(customUniqueIdValue.getBytes(StandardCharsets.UTF_8)),
			"is a valid Base64 encoding scheme");
	}
