	@TestFactory
	List<DynamicTest> findAllClassesInClasspathRootDelegates() throws Throwable {
		List<DynamicTest> tests = new ArrayList<>();
		List<Path> paths = new ArrayList<>();
		paths.add(Paths.get(".").toRealPath());
		paths.addAll(ReflectionUtils.getAllClasspathRootDirectories());
		for (Path path : paths) {
			URI root = path.toUri();
			String displayName = root.getPath();
			if (displayName.length() > 42) {
				displayName = "..." + displayName.substring(displayName.length() - 42);
			}
			tests.add(DynamicTest.dynamicTest(displayName,
				() -> assertEquals(ReflectionUtils.findAllClassesInClasspathRoot(root, allTypes, allNames),
					ReflectionSupport.findAllClassesInClasspathRoot(root, allTypes, allNames))));
		}
		return tests;
	}
