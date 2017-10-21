	@Test
	public void jacksonJsonViewWithResponseBodyAndJsonMessageConverter() throws Exception {
		Method method = JacksonController.class.getMethod("handleResponseBody");
		HandlerMethod handlerMethod = new HandlerMethod(new JacksonController(), method);
		MethodParameter methodReturnType = handlerMethod.getReturnType();

		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(new MappingJackson2HttpMessageConverter());

		RequestResponseBodyMethodProcessor processor = new RequestResponseBodyMethodProcessor(
				converters, null, Collections.singletonList(new JsonViewResponseBodyAdvice()));

		Object returnValue = new JacksonController().handleResponseBody();
		processor.handleReturnValue(returnValue, methodReturnType, this.container, this.request);

		String content = this.servletResponse.getContentAsString();
		assertFalse(content.contains("\"withView1\":\"with\""));
		assertTrue(content.contains("\"withView2\":\"with\""));
		assertFalse(content.contains("\"withoutView\":\"without\""));
	}
