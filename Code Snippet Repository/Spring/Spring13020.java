	@Test
	public void jacksonJsonViewWithResponseEntityAndJsonMessageConverter() throws Exception {
		Method method = JacksonController.class.getMethod("handleResponseEntity");
		HandlerMethod handlerMethod = new HandlerMethod(new JacksonController(), method);
		MethodParameter methodReturnType = handlerMethod.getReturnType();

		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(new MappingJackson2HttpMessageConverter());

		HttpEntityMethodProcessor processor = new HttpEntityMethodProcessor(
				converters, null, Collections.singletonList(new JsonViewResponseBodyAdvice()));

		Object returnValue = new JacksonController().handleResponseEntity();
		processor.handleReturnValue(returnValue, methodReturnType, this.container, this.request);

		String content = this.servletResponse.getContentAsString();
		assertFalse(content.contains("\"withView1\":\"with\""));
		assertTrue(content.contains("\"withView2\":\"with\""));
		assertFalse(content.contains("\"withoutView\":\"without\""));
	}
