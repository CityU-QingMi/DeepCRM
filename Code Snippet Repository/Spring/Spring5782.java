	@Nullable
	private Class<?> discoverPublicClass(Method method, Class<?> clazz) {
		if (Modifier.isPublic(clazz.getModifiers())) {
			try {
				clazz.getDeclaredMethod(method.getName(), method.getParameterTypes());
				return clazz;
			}
			catch (NoSuchMethodException ex) {
				// Continue below...
			}
		}
		Class<?>[] ifcs = clazz.getInterfaces();
		for (Class<?> ifc: ifcs) {
			discoverPublicClass(method, ifc);
		}
		if (clazz.getSuperclass() != null) {
			return discoverPublicClass(method, clazz.getSuperclass());
		}
		return null;
	}
