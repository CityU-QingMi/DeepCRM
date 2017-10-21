	private List<FrameworkMethod> getParametersMethods() throws Exception {
		List<FrameworkMethod> methods = getTestClass().getAnnotatedMethods(
				Parameterized.Parameters.class);
		SortedMap<Integer, FrameworkMethod> sortedMethods = new TreeMap<Integer, FrameworkMethod>();
		for (FrameworkMethod each : methods) {
			if (each.isPublic()) {
				if (!each.isStatic()) {
					if (getTestClass().getOnlyConstructor().getParameterCount() != 0) {
						throw new Exception("Method " + each.getMethod() + " is annotated with @Parameters, it is not static and there is no parameter-less constructor!");
					}
				}
				Order order = each.getAnnotation(Order.class);
				int value = order == null ? 0 : order.value();
				FrameworkMethod prev = sortedMethods.put(value, each);
				if (prev != null) {
					throw new Exception(String.format("There are more methods annotated with @Parameters and @Order(value=%d): %s (%s) and %s (%s)",
							value, prev.getMethod(), prev.getAnnotation(Order.class), each.getMethod(), order));
				}
			}
			else {
				throw new Exception("Method " + each.getMethod() + " is annotated with @Parameters but it is not public!");
			}
		}
		if (sortedMethods.isEmpty()) {
			throw new Exception("No public static parameters method on class "
					+ getTestClass().getName());
		}
		return new ArrayList<FrameworkMethod>(sortedMethods.values());
	}
