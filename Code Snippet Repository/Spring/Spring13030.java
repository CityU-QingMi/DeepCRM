	@Test
	public void jacksonSubTypeList() throws Exception {
		Method method = JacksonController.class.getMethod("handleSubTypeList");
		HandlerMethod handlerMethod = new HandlerMethod(new JacksonController(), method);
		MethodParameter methodReturnType = handlerMethod.getReturnType();

		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(new MappingJackson2HttpMessageConverter());
		RequestResponseBodyMethodProcessor processor = new RequestResponseBodyMethodProcessor(converters);

		Object returnValue = new JacksonController().handleSubTypeList();
		processor.handleReturnValue(returnValue, methodReturnType, this.container, this.request);

		String content = this.servletResponse.getContentAsString();
		assertTrue(content.contains("\"id\":123"));
		assertTrue(content.contains("\"name\":\"foo\""));
		assertTrue(content.contains("\"id\":456"));
		assertTrue(content.contains("\"name\":\"bar\""));
	}
