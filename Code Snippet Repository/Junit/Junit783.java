	@Test
	void resolvesClassesWithInheritedMethods() throws Exception {
		Class<?> superclass = PlainJUnit4TestCaseWithSingleTestWhichFails.class;
		Class<?> testClass = PlainJUnit4TestCaseWithSingleInheritedTestWhichFails.class;
		LauncherDiscoveryRequest discoveryRequest = discoveryRequestForClass(testClass);

		TestDescriptor engineDescriptor = discoverTests(discoveryRequest);

		TestDescriptor runnerDescriptor = getOnlyElement(engineDescriptor.getChildren());
		assertEquals(testClass.getName(), runnerDescriptor.getDisplayName());
		assertClassSource(testClass, runnerDescriptor);

		TestDescriptor testDescriptor = getOnlyElement(runnerDescriptor.getChildren());
		assertEquals("failingTest", testDescriptor.getDisplayName());
		assertMethodSource(superclass.getMethod("failingTest"), testDescriptor);
	}
