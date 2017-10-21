	@Test
	void findAllClassesInPackageWithinJarFile() throws Exception {
		URL jarfile = getClass().getResource("/jartest.jar");

		try (URLClassLoader classLoader = new URLClassLoader(new URL[] { jarfile })) {
			ClasspathScanner classpathScanner = new ClasspathScanner(() -> classLoader, ReflectionUtils::loadClass);

			List<Class<?>> classes = classpathScanner.scanForClassesInPackage("org.junit.platform.jartest.included",
				allClasses);
			assertThat(classes).hasSize(2);
			List<String> classNames = classes.stream().map(Class::getSimpleName).collect(Collectors.toList());
			assertTrue(classNames.contains("Included"));
			assertTrue(classNames.contains("RecursivelyIncluded"));
		}
	}
