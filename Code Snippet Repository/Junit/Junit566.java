	@Override
	protected Object convert(Object source, Class<?> targetType) {
		if (source == null) {
			if (targetType.isPrimitive()) {
				throw new ArgumentConversionException(
					"Cannot convert null to primitive value of type " + targetType.getName());
			}
			return null;
		}
		return convertToTargetType(source, toWrapperType(targetType));
	}
