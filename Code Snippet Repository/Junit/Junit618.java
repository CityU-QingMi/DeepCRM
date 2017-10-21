	private static List<Method> findAllMethodsInHierarchy(Class<?> clazz, HierarchyTraversalMode traversalMode) {
		Preconditions.notNull(clazz, "Class must not be null");
		Preconditions.notNull(traversalMode, "HierarchyTraversalMode must not be null");

		// @formatter:off
		List<Method> localMethods = getDeclaredMethods(clazz, traversalMode).stream()
				.filter(method -> !method.isSynthetic())
				.collect(toList());
		List<Method> superclassMethods = getSuperclassMethods(clazz, traversalMode).stream()
				.filter(method -> !isMethodShadowedByLocalMethods(method, localMethods))
				.collect(toList());
		List<Method> interfaceMethods = getInterfaceMethods(clazz, traversalMode).stream()
				.filter(method -> !isMethodShadowedByLocalMethods(method, localMethods))
				.collect(toList());
		// @formatter:on

		List<Method> methods = new ArrayList<>();
		if (traversalMode == TOP_DOWN) {
			methods.addAll(superclassMethods);
			methods.addAll(interfaceMethods);
		}
		methods.addAll(localMethods);
		if (traversalMode == BOTTOM_UP) {
			methods.addAll(interfaceMethods);
			methods.addAll(superclassMethods);
		}
		return methods;
	}
