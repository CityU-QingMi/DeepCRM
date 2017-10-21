	public static <T> T newInstance(Class<T> clazz, Object... args) {
		Preconditions.notNull(clazz, "Class must not be null");
		Preconditions.notNull(args, "Argument array must not be null");
		Preconditions.containsNoNullElements(args, "Individual arguments must not be null");

		try {
			Class<?>[] parameterTypes = Arrays.stream(args).map(Object::getClass).toArray(Class[]::new);
			return newInstance(clazz.getDeclaredConstructor(parameterTypes), args);
		}
		catch (Throwable t) {
			throw ExceptionUtils.throwAsUncheckedException(getUnderlyingCause(t));
		}
	}
