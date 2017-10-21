	protected Object toStoreValue(@Nullable Object userValue) {
		if (userValue == null) {
			if (this.allowNullValues) {
				return NullValue.INSTANCE;
			}
			throw new IllegalArgumentException(
					String.format("Cache '%s' is configured to not allow null " +
							"values but null was provided", getName()));
		}
		return userValue;
	}
