	static Class<?> getFieldType(Formatter<?> formatter) {
		Class<?> fieldType = GenericTypeResolver.resolveTypeArgument(formatter.getClass(), Formatter.class);
		if (fieldType == null && formatter instanceof DecoratingProxy) {
			fieldType = GenericTypeResolver.resolveTypeArgument(
					((DecoratingProxy) formatter).getDecoratedClass(), Formatter.class);
		}
		if (fieldType == null) {
			throw new IllegalArgumentException("Unable to extract the parameterized field type from Formatter [" +
					formatter.getClass().getName() + "]; does the class parameterize the <T> generic type?");
		}
		return fieldType;
	}
