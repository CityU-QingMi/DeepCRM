	private static boolean isFirstEntryInArray(Object value, @Nullable Object possibleArray) {
		if (possibleArray == null) {
			return false;
		}
		Class<?> type = possibleArray.getClass();
		if (!type.isArray() || Array.getLength(possibleArray) == 0 ||
				!ClassUtils.isAssignableValue(type.getComponentType(), value)) {
			return false;
		}
		Object arrayValue = Array.get(possibleArray, 0);
		return (type.getComponentType().isPrimitive() ? arrayValue.equals(value) : arrayValue == value);
	}
