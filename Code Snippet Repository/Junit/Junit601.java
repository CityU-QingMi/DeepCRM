	private List<Class<?>> findClassesForPath(Path baseDir, String basePackageName, ClassFilter classFilter) {
		Preconditions.condition(Files.exists(baseDir), () -> "baseDir must exist: " + baseDir);
		List<Class<?>> classes = new ArrayList<>();
		try {
			Files.walkFileTree(baseDir, new ClassFileVisitor(
				classFile -> processClassFileSafely(baseDir, basePackageName, classFilter, classFile, classes::add)));
		}
		catch (IOException ex) {
			logger.warn(ex, () -> "I/O error scanning files in " + baseDir);
		}
		return classes;
	}
