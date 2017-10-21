	@Test
	@ExtendWith(TempDirectory.class)
	void getAllClasspathRootDirectories(@Root Path tempDirectory) throws Exception {
		Path root1 = tempDirectory.resolve("root1").toAbsolutePath();
		Path root2 = tempDirectory.resolve("root2").toAbsolutePath();
		String testClassPath = root1 + File.pathSeparator + root2;

		String originalClassPath = System.setProperty("java.class.path", testClassPath);
		try {
			createDirectories(root1, root2);

			assertThat(ReflectionUtils.getAllClasspathRootDirectories()).containsOnly(root1, root2);
		}
		finally {
			System.setProperty("java.class.path", originalClassPath);
		}
	}
