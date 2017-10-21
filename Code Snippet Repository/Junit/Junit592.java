	public static <T> Optional<T> getDefaultValue(Annotation annotation, String attributeName, Class<T> attributeType) {
		Preconditions.notNull(annotation, "Annotation must not be null");
		Preconditions.notBlank(attributeName, "attributeName must not be null or blank");
		Preconditions.notNull(attributeType, "attributeType must not be null");

		Class<? extends Annotation> annotationType = annotation.annotationType();
		Method attribute = null;
		try {
			attribute = annotationType.getDeclaredMethod(attributeName);
		}
		catch (Exception ex) {
			return Optional.empty();
		}

		Object defaultValue = attribute.getDefaultValue();
		if (defaultValue == null) {
			return Optional.empty();
		}

		Preconditions.condition(attributeType.isInstance(defaultValue),
			() -> String.format("Attribute '%s' in annotation %s is of type %s, not %s", attributeName,
				annotationType.getName(), defaultValue.getClass().getName(), attributeType.getName()));

		return Optional.of(attributeType.cast(defaultValue));

	}
