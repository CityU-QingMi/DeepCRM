	@Nullable
	private Method getFinder(Class<?> entityClass) {
		String finderMethod = "find" + getEntityName(entityClass);
		Method[] methods;
		boolean localOnlyFiltered;
		try {
			methods = entityClass.getDeclaredMethods();
			localOnlyFiltered = true;
		}
		catch (SecurityException ex) {
			// Not allowed to access non-public methods...
			// Fallback: check locally declared public methods only.
			methods = entityClass.getMethods();
			localOnlyFiltered = false;
		}
		for (Method method : methods) {
			if (Modifier.isStatic(method.getModifiers()) && method.getName().equals(finderMethod) &&
					method.getParameterCount() == 1 && method.getReturnType().equals(entityClass) &&
					(localOnlyFiltered || method.getDeclaringClass().equals(entityClass))) {
				return method;
			}
		}
		return null;
	}
