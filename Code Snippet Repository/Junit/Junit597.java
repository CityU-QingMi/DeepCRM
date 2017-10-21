	public static List<Field> findPublicAnnotatedFields(Class<?> clazz, Class<?> fieldType,
			Class<? extends Annotation> annotationType) {

		Preconditions.notNull(clazz, "Class must not be null");
		Preconditions.notNull(fieldType, "fieldType must not be null");
		Preconditions.notNull(annotationType, "annotationType must not be null");

		// @formatter:off
		return Arrays.stream(clazz.getFields())
				.filter(field -> fieldType.isAssignableFrom(field.getType()) && isAnnotated(field, annotationType))
				.collect(toUnmodifiableList());
		// @formatter:on
	}
