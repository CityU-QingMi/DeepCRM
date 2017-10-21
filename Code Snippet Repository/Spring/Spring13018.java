	@Test
	public void supportsReturnTypeRestController() throws Exception {
		Method method = TestRestController.class.getMethod("handle");
		MethodParameter returnType = new MethodParameter(method, -1);

		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(new StringHttpMessageConverter());

		RequestResponseBodyMethodProcessor processor = new RequestResponseBodyMethodProcessor(converters);

		assertTrue("Failed to recognize type-level @RestController", processor.supportsReturnType(returnType));
	}
