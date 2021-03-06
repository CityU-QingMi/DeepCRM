	@Test
	void packageResolutionUsingDefaultPackage() {
		resolver.resolveSelectors(request().selectors(selectPackage("")).build(), engineDescriptor);

		// 150 is completely arbitrary. The actual number is likely much higher.
		assertThat(engineDescriptor.getDescendants().size())//
				.describedAs("Too few test descriptors in classpath")//
				.isGreaterThan(150);

		List<UniqueId> uniqueIds = uniqueIds();
		assertThat(uniqueIds)//
				.describedAs("Failed to pick up DefaultPackageTestCase via classpath scanning")//
				.contains(uniqueIdForClass(ReflectionUtils.loadClass("DefaultPackageTestCase").get()));
		assertThat(uniqueIds).contains(uniqueIdForClass(Class1WithTestCases.class));
		assertThat(uniqueIds).contains(uniqueIdForMethod(Class1WithTestCases.class, "test1()"));
		assertThat(uniqueIds).contains(uniqueIdForClass(Class2WithTestCases.class));
		assertThat(uniqueIds).contains(uniqueIdForMethod(Class2WithTestCases.class, "test2()"));
	}
