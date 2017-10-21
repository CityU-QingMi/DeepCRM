	@Override
	protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException {
		String value = this.stringHttpMessageConverter.readInternal(String.class, inputMessage);
		Object result = this.conversionService.convert(value, clazz);
		if (result == null) {
			throw new HttpMessageConversionException(
					"Unexpected null conversion result for '" + value + "' to " + clazz);
		}
		return result;
	}
