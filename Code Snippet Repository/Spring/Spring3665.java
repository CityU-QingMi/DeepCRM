		@Override
		@SuppressWarnings("")
		public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
			if (Integer.class == targetType) {
				return (Converter<String, T>) new IntegerConverter();
			}
			else {
				throw new IllegalStateException();
			}
		}
