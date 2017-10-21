	@SuppressWarnings("")
	private <T> T castToRequiredType(Object key, Object value, Class<T> requiredType) {
		if (value == null) {
			return null;
		}
		if (isAssignableTo(value, requiredType)) {
			if (requiredType.isPrimitive()) {
				return (T) getWrapperType(requiredType).cast(value);
			}
			return requiredType.cast(value);
		}
		// else
		throw new ExtensionContextException(
			String.format("Object stored under key [%s] is not of required type [%s]", key, requiredType.getName()));
	}
