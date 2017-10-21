	@Test
	void resolvesClasspathSelectorForJarFile() throws Exception {
		URL jarUrl = getClass().getResource("/vintage-testjar.jar");
		Path jarFile = Paths.get(jarUrl.toURI());

		ClassLoader originalClassLoader = Thread.currentThread().getContextClassLoader();
		try (URLClassLoader classLoader = new URLClassLoader(new URL[] { jarUrl })) {
			Thread.currentThread().setContextClassLoader(classLoader);

			LauncherDiscoveryRequest discoveryRequest = request().selectors(
				selectClasspathRoots(singleton(jarFile))).build();
			TestDescriptor engineDescriptor = discoverTests(discoveryRequest);

			// @formatter:off
			assertThat(engineDescriptor.getChildren())
					.extracting(TestDescriptor::getDisplayName)
					.containsExactly("com.example.project.JUnit4Test");
			// @formatter:on
		}
		finally {
			Thread.currentThread().setContextClassLoader(originalClassLoader);
		}
	}
