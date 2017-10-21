	@Nullable
	protected Field findField(String name, Class<?> clazz, boolean mustBeStatic) {
		Field[] fields = clazz.getFields();
		for (Field field : fields) {
			if (field.getName().equals(name) && (!mustBeStatic || Modifier.isStatic(field.getModifiers()))) {
				return field;
			}
		}
		// We'll search superclasses and implemented interfaces explicitly,
		// although it shouldn't be necessary - however, see SPR-10125.
		if (clazz.getSuperclass() != null) {
			Field field = findField(name, clazz.getSuperclass(), mustBeStatic);
			if (field != null) {
				return field;
			}
		}
		for (Class<?> implementedInterface : clazz.getInterfaces()) {
			Field field = findField(name, implementedInterface, mustBeStatic);
			if (field != null) {
				return field;
			}
		}
		return null;
	}
