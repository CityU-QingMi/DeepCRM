	@SuppressWarnings("")
	private static List<String> moduleDirectoryNames() throws IOException {
		// @formatter:off
		String startOfModuleLine = "include '";
		try (Stream<String> stream = Files.lines(Paths.get("../settings.gradle"))
				.filter(line -> line.startsWith(startOfModuleLine))
				.map(line -> line.substring(startOfModuleLine.length(), line.length() - 1))
				.filter(name -> !name.equals("junit-platform-console-standalone"))
				.filter(name -> name.startsWith("junit-"))) {
			return stream.collect(Collectors.toList());
		}
		// @formatter:on
	}
