	@Nullable
	protected Method findGetterForProperty(String propertyName, Class<?> clazz, boolean mustBeStatic) {
		Method method = findMethodForProperty(getPropertyMethodSuffixes(propertyName),
				 "get", clazz, mustBeStatic, 0, ANY_TYPES);
		if (method == null) {
			method = findMethodForProperty(getPropertyMethodSuffixes(propertyName),
					 "is", clazz, mustBeStatic, 0, BOOLEAN_TYPES);
		}
		return method;
	}
