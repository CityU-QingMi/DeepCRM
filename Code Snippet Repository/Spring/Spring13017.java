	@Test
	public void supportsReturnTypeResponseBodyOnType() throws Exception {
		Method method = ResponseBodyController.class.getMethod("handle");
		MethodParameter returnType = new MethodParameter(method, -1);

		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(new StringHttpMessageConverter());

		RequestResponseBodyMethodProcessor processor = new RequestResponseBodyMethodProcessor(converters);

		assertTrue("Failed to recognize type-level @ResponseBody", processor.supportsReturnType(returnType));
	}
