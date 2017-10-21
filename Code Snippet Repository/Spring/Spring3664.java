	private <T> void registerDefaultValue(Class<T> clazz, final T defaultValue) {
		formattingService.addFormatterForFieldType(clazz, new Formatter<T>() {
			@Override
			public T parse(String text, Locale locale) throws ParseException {
				return defaultValue;
			}
			@Override
			public String print(T t, Locale locale) {
				return defaultValue.toString();
			}
			@Override
			public String toString() {
				return defaultValue.toString();
			}
		});
	}
