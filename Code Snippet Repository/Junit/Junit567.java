	private Object convertToTargetType(Object source, Class<?> targetType) {
		if (targetType.isInstance(source)) {
			return source;
		}
		if (source instanceof String) {
			Optional<StringToObjectConverter> converter = stringToObjectConverters.stream().filter(
				candidate -> candidate.canConvert(targetType)).findFirst();
			if (converter.isPresent()) {
				try {
					return converter.get().convert((String) source, targetType);
				}
				catch (Exception ex) {
					throw new ArgumentConversionException(
						"Failed to convert String [" + source + "] to type " + targetType.getName(), ex);
				}
			}
		}
		throw new ArgumentConversionException("No implicit conversion to convert object of type "
				+ source.getClass().getName() + " to type " + targetType.getName());
	}
