	@Test
	public void jacksonTypeInfoList() throws Exception {
		Method method = JacksonController.class.getMethod("handleList");
		HandlerMethod handlerMethod = new HandlerMethod(new JacksonController(), method);
		MethodParameter methodReturnType = handlerMethod.getReturnType();

		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(new MappingJackson2HttpMessageConverter());
		HttpEntityMethodProcessor processor = new HttpEntityMethodProcessor(converters);

		Object returnValue = new JacksonController().handleList();
		processor.handleReturnValue(returnValue, methodReturnType, this.mavContainer, this.webRequest);

		String content = this.servletResponse.getContentAsString();
		assertTrue(content.contains("\"type\":\"foo\""));
		assertTrue(content.contains("\"type\":\"bar\""));
	}
