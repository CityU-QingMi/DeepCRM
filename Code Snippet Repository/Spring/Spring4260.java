	@SuppressWarnings("")
	public static Properties createStringAdaptingProperties() {
		return new Properties() {
			@Override
			@Nullable
			public String getProperty(String key) {
				Object value = get(key);
				return (value != null ? value.toString() : null);
			}
		};
	}
