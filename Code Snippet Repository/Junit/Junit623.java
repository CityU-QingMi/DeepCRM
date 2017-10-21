	private static List<Method> getInterfaceMethods(Class<?> clazz, HierarchyTraversalMode traversalMode) {
		List<Method> allInterfaceMethods = new ArrayList<>();
		for (Class<?> ifc : clazz.getInterfaces()) {

			// @formatter:off
			List<Method> localInterfaceMethods = getMethods(ifc).stream()
					.filter(m -> !isAbstract(m))
					.collect(toList());

			List<Method> superinterfaceMethods = getInterfaceMethods(ifc, traversalMode).stream()
					.filter(method -> !isMethodShadowedByLocalMethods(method, localInterfaceMethods))
					.collect(toList());
			// @formatter:on

			if (traversalMode == TOP_DOWN) {
				allInterfaceMethods.addAll(superinterfaceMethods);
			}
			allInterfaceMethods.addAll(localInterfaceMethods);
			if (traversalMode == BOTTOM_UP) {
				allInterfaceMethods.addAll(superinterfaceMethods);
			}
		}
		return allInterfaceMethods;
	}
