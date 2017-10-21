	@Test
	void onlyLoadsClassesInClasspathRootThatAreIncludedByTheClassNameFilter() throws Exception {
		ClassFilter classFilter = ClassFilter.of(name -> ClasspathScannerTests.class.getName().equals(name),
			type -> true);
		URI root = getTestClasspathRoot();

		classpathScanner.scanForClassesInClasspathRoot(root, classFilter);

		assertThat(loadedClasses).containsExactly(ClasspathScannerTests.class);
	}
