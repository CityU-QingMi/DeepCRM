	@Nullable
	private Field getField() {
		String name = getName();
		if (!StringUtils.hasLength(name)) {
			return null;
		}
		Field field = null;
		Class<?> declaringClass = declaringClass();
		if (declaringClass != null) {
			field = ReflectionUtils.findField(declaringClass, name);
			if (field == null) {
				// Same lenient fallback checking as in CachedIntrospectionResults...
				field = ReflectionUtils.findField(declaringClass,
						name.substring(0, 1).toLowerCase() + name.substring(1));
				if (field == null) {
					field = ReflectionUtils.findField(declaringClass,
							name.substring(0, 1).toUpperCase() + name.substring(1));
				}
			}
		}
		return field;
	}
