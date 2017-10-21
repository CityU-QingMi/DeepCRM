	private static List<Method> getDeclaredMethods(Class<?> clazz, HierarchyTraversalMode traversalMode) {
		// Note: getDefaultMethods() already sorts the methods,
		List<Method> defaultMethods = getDefaultMethods(clazz);
		List<Method> declaredMethods = toSortedMutableList(clazz.getDeclaredMethods());

		// Take the traversal mode into account in order to retain the inherited
		// nature of interface default methods.
		if (traversalMode == BOTTOM_UP) {
			declaredMethods.addAll(defaultMethods);
			return declaredMethods;
		}
		else {
			defaultMethods.addAll(declaredMethods);
			return defaultMethods;
		}
	}
