	@Test
	void findAllClassesInDefaultPackageInClasspathRoot() throws Exception {
		ClassFilter classFilter = ClassFilter.of(this::inDefaultPackage);
		List<Class<?>> classes = classpathScanner.scanForClassesInClasspathRoot(getTestClasspathRoot(), classFilter);

		assertEquals(1, classes.size(), "number of classes found in default package");
		Class<?> testClass = classes.get(0);
		assertTrue(inDefaultPackage(testClass));
		assertEquals("DefaultPackageTestCase", testClass.getName());
	}
