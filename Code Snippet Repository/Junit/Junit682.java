	public static List<ClasspathRootSelector> selectClasspathRoots(Set<Path> classpathRoots) {
		Preconditions.notNull(classpathRoots, "classpathRoots must not be null");

		// @formatter:off
		return classpathRoots.stream()
				.filter(Files::exists)
				.map(Path::toUri)
				.map(ClasspathRootSelector::new)
				// unmodifiable since selectClasspathRoots is a public, non-internal method
				.collect(toUnmodifiableList());
		// @formatter:on
	}
