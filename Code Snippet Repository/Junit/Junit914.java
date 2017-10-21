		@Override
		public void execute() throws Throwable {
			ConsoleLauncherWrapper wrapper = new ConsoleLauncherWrapper();
			ConsoleLauncherWrapperResult result = wrapper.execute(Optional.empty(), args);

			String resourceName = dirName + "/" + outName;
			Optional<URL> optionalUrl = Optional.ofNullable(getClass().getClassLoader().getResource(resourceName));
			if (!optionalUrl.isPresent()) {
				if (Boolean.getBoolean("org.junit.platform.console.ConsoleDetailsTests.writeResultOut")) {
					// do not use Files.createTempDirectory(prefix) as we want one folder for one container
					Path temp = Paths.get(System.getProperty("java.io.tmpdir"), dirName.replace('/', '-'));
					Files.createDirectories(temp);
					Path path = Files.write(temp.resolve(outName), result.out.getBytes(UTF_8));
					assumeTrue(false,
						format("resource `%s` not found\nwrote console stdout to: %s", resourceName, path));
				}
				fail("could not load resource named `" + resourceName + "`");
			}

			URL url = optionalUrl.orElseThrow(AssertionError::new);
			Path path = Paths.get(url.toURI());
			assumeTrue(Files.exists(path), "path does not exist: " + path);
			assumeTrue(Files.isReadable(path), "can not read: " + path);

			List<String> expectedLines = Files.readAllLines(path, UTF_8);
			List<String> actualLines = asList(result.out.split("\\R"));

			assertLinesMatch(expectedLines, actualLines);
		}
