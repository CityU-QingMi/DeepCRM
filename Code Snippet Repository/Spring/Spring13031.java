	@Test
	public void defaultCharset() throws Exception {
		Method method = JacksonController.class.getMethod("defaultCharset");
		HandlerMethod handlerMethod = new HandlerMethod(new JacksonController(), method);
		MethodParameter methodReturnType = handlerMethod.getReturnType();

		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(new MappingJackson2HttpMessageConverter());
		RequestResponseBodyMethodProcessor processor = new RequestResponseBodyMethodProcessor(converters);

		Object returnValue = new JacksonController().defaultCharset();
		processor.handleReturnValue(returnValue, methodReturnType, this.container, this.request);

		assertEquals("UTF-8", this.servletResponse.getCharacterEncoding());
	}
