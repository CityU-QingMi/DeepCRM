	static void assertFileContents(final int runNumber) throws IOException {
		final Path path = Paths.get(FOLDER + "/audit.tmp");
		final List<String> lines = Files.readAllLines(path, Charset.defaultCharset());
		int i = 1;
		final int size = lines.size();
		for (final String string : lines) {
			if (string.startsWith(",,")) {
				final Path folder = Paths.get(FOLDER);
				final File[] files = folder.toFile().listFiles();
				Arrays.sort(files);
				System.out.println("Run " + runNumber + ": " + Arrays.toString(files));
				Assert.fail(
						String.format("Run %,d, line %,d of %,d: \"%s\" in %s", runNumber, i++, size, string, lines));
			}
		}
	}
