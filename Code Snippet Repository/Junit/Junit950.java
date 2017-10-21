	@Test
	void usesSameClassLoaderIfNoAdditionalClassPathEntriesArePresent() throws Exception {
		options.setAdditionalClasspathEntries(emptyList());

		ClassLoader oldClassLoader = getDefaultClassLoader();
		dummyTestEngine.addTest("failingTest",
			() -> assertNotSame(oldClassLoader, getDefaultClassLoader(), "should fail"));

		ConsoleTestExecutor task = new ConsoleTestExecutor(options, () -> createLauncher(dummyTestEngine));
		task.execute(new PrintWriter(stringWriter));

		assertThat(stringWriter.toString()).contains("failingTest", "should fail", "1 tests failed");
	}
