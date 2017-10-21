	@Test
	void usesCustomClassLoaderIfAdditionalClassPathEntriesArePresent() throws Exception {
		options.setAdditionalClasspathEntries(singletonList(Paths.get(".")));

		ClassLoader oldClassLoader = getDefaultClassLoader();
		dummyTestEngine.addTest("failingTest",
			() -> assertSame(oldClassLoader, getDefaultClassLoader(), "should fail"));

		ConsoleTestExecutor task = new ConsoleTestExecutor(options, () -> createLauncher(dummyTestEngine));
		task.execute(new PrintWriter(stringWriter));

		assertThat(stringWriter.toString()).contains("failingTest", "should fail", "1 tests failed");
	}
