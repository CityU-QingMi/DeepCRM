	private void scanForClassesInClasspathRootWithinJarFile(String resourceName) throws Exception {
		URL jarfile = getClass().getResource(resourceName);

		try (URLClassLoader classLoader = new URLClassLoader(new URL[] { jarfile })) {
			ClasspathScanner classpathScanner = new ClasspathScanner(() -> classLoader, ReflectionUtils::loadClass);

			List<Class<?>> classes = classpathScanner.scanForClassesInClasspathRoot(jarfile.toURI(), allClasses);
			List<String> classNames = classes.stream().map(Class::getName).collect(Collectors.toList());
			assertThat(classNames).hasSize(3) //
					.contains("org.junit.platform.jartest.notincluded.NotIncluded",
						"org.junit.platform.jartest.included.recursive.RecursivelyIncluded",
						"org.junit.platform.jartest.included.Included");
		}
	}
