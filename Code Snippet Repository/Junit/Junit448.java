	@Test
	void classpathResolutionForJarFiles() throws Exception {
		URL jarUrl = getClass().getResource("/jupiter-testjar.jar");
		Path path = Paths.get(jarUrl.toURI());
		List<ClasspathRootSelector> selectors = selectClasspathRoots(singleton(path));

		ClassLoader originalClassLoader = Thread.currentThread().getContextClassLoader();
		try (URLClassLoader classLoader = new URLClassLoader(new URL[] { jarUrl })) {
			Thread.currentThread().setContextClassLoader(classLoader);

			resolver.resolveSelectors(request().selectors(selectors).build(), engineDescriptor);

			assertThat(uniqueIds()) //
					.contains(uniqueIdForTopLevelClass("com.example.project.FirstTest")) //
					.contains(uniqueIdForTopLevelClass("com.example.project.SecondTest"));
		}
		finally {
			Thread.currentThread().setContextClassLoader(originalClassLoader);
		}
	}
