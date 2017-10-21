	private static Optional<Method> findMethod(Class<?> clazz, Predicate<Method> predicate) {
		Preconditions.notNull(clazz, "Class must not be null");
		Preconditions.notNull(predicate, "Predicate must not be null");

		for (Class<?> current = clazz; current != null && current != Object.class; current = current.getSuperclass()) {
			// Search for match in current type
			List<Method> methods = current.isInterface() ? getMethods(current) : getDeclaredMethods(current, BOTTOM_UP);
			for (Method method : methods) {
				if (predicate.test(method)) {
					return Optional.of(method);
				}
			}

			// Search for match in interfaces implemented by current type
			for (Class<?> ifc : current.getInterfaces()) {
				Optional<Method> optional = findMethod(ifc, predicate);
				if (optional.isPresent()) {
					return optional;
				}
			}
		}

		return Optional.empty();
	}
