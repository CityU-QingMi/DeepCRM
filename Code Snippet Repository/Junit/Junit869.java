	@Test
	void scanForClassesInClasspathRootWhenOutOfMemoryErrorOccurs() throws Exception {
		Predicate<Class<?>> outOfMemoryErrorSimulationFilter = clazz -> {
			if (clazz.getSimpleName().equals(ClassForOutOfMemoryErrorSimulation.class.getSimpleName())) {
				throw new OutOfMemoryError();
			}
			return true;
		};
		ClassFilter classFilter = ClassFilter.of(outOfMemoryErrorSimulationFilter);

		assertThrows(OutOfMemoryError.class,
			() -> this.classpathScanner.scanForClassesInClasspathRoot(getTestClasspathRoot(), classFilter));
	}
