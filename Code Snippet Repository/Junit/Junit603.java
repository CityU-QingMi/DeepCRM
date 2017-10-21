	private String determineFullyQualifiedClassName(Path baseDir, String basePackageName, Path classFile) {
		// @formatter:off
		return Stream.of(
					basePackageName,
					determineSubpackageName(baseDir, classFile),
					determineSimpleClassName(classFile)
				)
				.filter(value -> !value.isEmpty()) // Handle default package appropriately.
				.collect(joining(PACKAGE_SEPARATOR_STRING));
		// @formatter:on
	}
