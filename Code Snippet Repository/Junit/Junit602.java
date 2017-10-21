	private void processClassFileSafely(Path baseDir, String basePackageName, ClassFilter classFilter, Path classFile,
			Consumer<Class<?>> classConsumer) {
		try {
			String fullyQualifiedClassName = determineFullyQualifiedClassName(baseDir, basePackageName, classFile);
			if (classFilter.match(fullyQualifiedClassName)) {
				try {
					// @formatter:off
					loadClass.apply(fullyQualifiedClassName, getClassLoader())
							.filter(classFilter) // Always use ".filter(classFilter)" to include future predicates.
							.ifPresent(classConsumer);
					// @formatter:on
				}
				catch (InternalError internalError) {
					handleInternalError(classFile, fullyQualifiedClassName, internalError);
				}
			}
		}
		catch (Throwable throwable) {
			handleThrowable(classFile, throwable);
		}
	}
