	@Test
	void doesNotResolveMethodOfClassNotAcceptedByClassNameFilter() throws Exception {
		Class<?> testClass = PlainJUnit4TestCaseWithFiveTestMethods.class;
		// @formatter:off
		LauncherDiscoveryRequest request = request()
				.selectors(selectMethod(testClass, testClass.getMethod("failingTest")))
				.filters(includeClassNamePatterns("Foo"))
				.build();
		// @formatter:on

		assertYieldsNoDescriptors(request);
	}
