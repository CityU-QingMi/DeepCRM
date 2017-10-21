	private static void findNestedClasses(Class<?> clazz, Set<Class<?>> candidates) {
		if (clazz == Object.class || clazz == null) {
			return;
		}

		// Candidates in current class
		candidates.addAll(Arrays.asList(clazz.getDeclaredClasses()));

		// Search class hierarchy
		findNestedClasses(clazz.getSuperclass(), candidates);

		// Search interface hierarchy
		for (Class<?> ifc : clazz.getInterfaces()) {
			findNestedClasses(ifc, candidates);
		}
	}
