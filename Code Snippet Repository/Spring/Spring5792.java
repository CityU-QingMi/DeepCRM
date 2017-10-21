	@Nullable
	private Method findMethodForProperty(String[] methodSuffixes, String prefix, Class<?> clazz,
			boolean mustBeStatic, int numberOfParams, Set<Class<?>> requiredReturnTypes) {

		Method[] methods = getSortedClassMethods(clazz);
		for (String methodSuffix : methodSuffixes) {
			for (Method method : methods) {
				if (method.getName().equals(prefix + methodSuffix) &&
						method.getParameterCount() == numberOfParams &&
						(!mustBeStatic || Modifier.isStatic(method.getModifiers())) &&
						(requiredReturnTypes.isEmpty() || requiredReturnTypes.contains(method.getReturnType()))) {
					return method;
				}
			}
		}
		return null;

	}
