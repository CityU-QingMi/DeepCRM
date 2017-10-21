	private Collection<Method> getMethods(Class<?> type, Object targetObject) {
		if (targetObject instanceof Class) {
			Set<Method> result = new LinkedHashSet<>();
			// Add these so that static methods are invocable on the type: e.g. Float.valueOf(..)
			Method[] methods = getMethods(type);
			for (Method method : methods) {
				if (Modifier.isStatic(method.getModifiers())) {
					result.add(method);
				}
			}
			// Also expose methods from java.lang.Class itself
			result.addAll(Arrays.asList(getMethods(Class.class)));
			return result;
		}
		else {
			return Arrays.asList(getMethods(type));
		}
	}
