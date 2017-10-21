	@Test
	public void jacksonTypeInfoList() throws Exception {
		Method method = JacksonController.class.getMethod("handleTypeInfoList");
		HandlerMethod handlerMethod = new HandlerMethod(new JacksonController(), method);
		MethodParameter methodReturnType = handlerMethod.getReturnType();

		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(new MappingJackson2HttpMessageConverter());
		RequestResponseBodyMethodProcessor processor = new RequestResponseBodyMethodProcessor(converters);

		Object returnValue = new JacksonController().handleTypeInfoList();
		processor.handleReturnValue(returnValue, methodReturnType, this.container, this.request);

		String content = this.servletResponse.getContentAsString();
		assertTrue(content.contains("\"type\":\"foo\""));
		assertTrue(content.contains("\"type\":\"bar\""));
	}
