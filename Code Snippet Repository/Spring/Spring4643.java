	private String styleArray(Object[] array) {
		StringBuilder result = new StringBuilder(array.length * 8 + 16);
		result.append(ARRAY + "<").append(ClassUtils.getShortName(array.getClass().getComponentType())).append(">[");
		for (int i = 0; i < array.length - 1; i++) {
			result.append(style(array[i]));
			result.append(',').append(' ');
		}
		if (array.length > 0) {
			result.append(style(array[array.length - 1]));
		}
		else {
			result.append(EMPTY);
		}
		result.append("]");
		return result.toString();
	}
