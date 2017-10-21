	@Test
	void doesNotResolveMethodOfClassNotAcceptedByPackageNameFilter() throws Exception {
		Class<?> testClass = PlainJUnit4TestCaseWithFiveTestMethods.class;
		// @formatter:off
		LauncherDiscoveryRequest request = request()
				.selectors(selectMethod(testClass, testClass.getMethod("failingTest")))
				.filters(includePackageNames("com.acme"))
				.build();
		// @formatter:on

		assertYieldsNoDescriptors(request);
	}
