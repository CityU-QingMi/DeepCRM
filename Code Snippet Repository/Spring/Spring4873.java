	public static String[] trimArrayElements(@Nullable String[] array) {
		if (ObjectUtils.isEmpty(array)) {
			return new String[0];
		}

		String[] result = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			String element = array[i];
			result[i] = (element != null ? element.trim() : null);
		}
		return result;
	}
