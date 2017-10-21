	@Test
	public void jacksonJsonViewWithResponseEntityAndXmlMessageConverter() throws Exception {
		Method method = JacksonController.class.getMethod("handleResponseEntity");
		HandlerMethod handlerMethod = new HandlerMethod(new JacksonController(), method);
		MethodParameter methodReturnType = handlerMethod.getReturnType();

		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(new MappingJackson2XmlHttpMessageConverter());

		HttpEntityMethodProcessor processor = new HttpEntityMethodProcessor(
				converters, null, Collections.singletonList(new JsonViewResponseBodyAdvice()));

		Object returnValue = new JacksonController().handleResponseEntity();
		processor.handleReturnValue(returnValue, methodReturnType, this.container, this.request);

		String content = this.servletResponse.getContentAsString();
		assertFalse(content.contains("<withView1>with</withView1>"));
		assertTrue(content.contains("<withView2>with</withView2>"));
		assertFalse(content.contains("<withoutView>without</withoutView>"));
	}
