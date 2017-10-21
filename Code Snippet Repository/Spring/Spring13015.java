	@Test
	public void handleReturnValueImage() throws Exception {
		this.servletRequest.addHeader("Accept", "*/*");

		Method method = getClass().getDeclaredMethod("getImage");
		MethodParameter returnType = new MethodParameter(method, -1);

		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(new ResourceHttpMessageConverter());
		RequestResponseBodyMethodProcessor processor = new RequestResponseBodyMethodProcessor(converters);

		ClassPathResource resource = new ClassPathResource("logo.jpg", getClass());
		processor.writeWithMessageConverters(resource, returnType, this.request);

		assertEquals("image/jpeg", this.servletResponse.getHeader("Content-Type"));
	}
