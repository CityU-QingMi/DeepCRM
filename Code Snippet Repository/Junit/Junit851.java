	@ParameterizedTest
	@MethodSource("")
	void automaticModuleName(String module) {
		String expected = "org." + module.replace('-', '.');
		String jarName = module + "-" + version(module) + ".jar";
		Path jarPath = Paths.get("..", module).resolve("build/libs").resolve(jarName).normalize();
		try (JarFile jarFile = new JarFile(jarPath.toFile())) {
			// first, check automatic module name
			Manifest manifest = jarFile.getManifest();
			String automaticModuleName = manifest.getMainAttributes().getValue("Automatic-Module-Name");
			assertNotNull(automaticModuleName, "`Automatic-Module-Name` not found in manifest of JAR: " + jarPath);
			assertEquals(expected, automaticModuleName);
			// second, check entries are located in matching packages
			String expectedStartOfPackageName = expected.replace('.', '/');
			// @formatter:off
			List<String> unexpectedNames = jarFile.stream()
					.map(ZipEntry::getName)
					.filter(n -> n.endsWith(".class"))
					.filter(n -> !n.startsWith(expectedStartOfPackageName))
					.collect(toList());
			// @formatter:on
			assertTrue(unexpectedNames.isEmpty(), unexpectedNames.size()
					+ " entries are not located in (a sub-) package of " + expectedStartOfPackageName);
		}
		catch (IOException e) {
			fail("test jar file failed: " + e, e);
		}
	}
