	public static <T> Optional<Object> readFieldValue(Class<T> clazz, String fieldName, T instance) {
		Preconditions.notNull(clazz, "Class must not be null");
		Preconditions.notBlank(fieldName, "Field name must not be null or blank");

		try {
			Field field = makeAccessible(clazz.getDeclaredField(fieldName));
			return Optional.ofNullable(field.get(instance));
		}
		catch (Throwable t) {
			BlacklistedExceptions.rethrowIfBlacklisted(t);
			return Optional.empty();
		}
	}
