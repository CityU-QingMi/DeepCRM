	@SuppressWarnings("")
	private void verifyRequestResponseBodyAdvice(Object bean) {
		assertNotNull(bean);
		Object value = new DirectFieldAccessor(bean).getPropertyValue("requestResponseBodyAdvice");
		assertNotNull(value);
		assertTrue(value instanceof List);
		List<ResponseBodyAdvice> converters = (List<ResponseBodyAdvice>) value;
		assertTrue(converters.get(0) instanceof JsonViewRequestBodyAdvice);
		assertTrue(converters.get(1) instanceof JsonViewResponseBodyAdvice);
	}
