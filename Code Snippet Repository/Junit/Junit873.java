	@Test
	@ExtendWith(TempDirectory.class)
	void doesNotLoopInfinitelyWithCircularSymlinks(@Root Path tempDir) throws Exception {

		// Abort if running on Microsoft Windows since we are testing symbolic links
		assumeFalse(System.getProperty("os.name").toLowerCase().contains("win"));

		Path directory = Files.createDirectory(tempDir.resolve("directory"));
		Path symlink1 = Files.createSymbolicLink(tempDir.resolve("symlink1"), directory);
		Files.createSymbolicLink(directory.resolve("symlink2"), symlink1);

		List<Class<?>> classes = classpathScanner.scanForClassesInClasspathRoot(symlink1.toUri(), allClasses);

		assertThat(classes).isEmpty();
	}
