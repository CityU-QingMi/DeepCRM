	@Test
	public void resolveArgumentWithJacksonJsonViewAndXmlMessageConverter() throws Exception {
		String content = "<root><withView1>with</withView1><withView2>with</withView2><withoutView>without</withoutView></root>";
		this.servletRequest.setContent(content.getBytes("UTF-8"));
		this.servletRequest.setContentType(MediaType.APPLICATION_XML_VALUE);

		Method method = JacksonController.class.getMethod("handleRequestBody", JacksonViewBean.class);
		HandlerMethod handlerMethod = new HandlerMethod(new JacksonController(), method);
		MethodParameter methodParameter = handlerMethod.getMethodParameters()[0];

		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(new MappingJackson2XmlHttpMessageConverter());

		RequestResponseBodyMethodProcessor processor = new RequestResponseBodyMethodProcessor(
				converters, null, Collections.singletonList(new JsonViewRequestBodyAdvice()));

		@SuppressWarnings("unchecked")
		JacksonViewBean result = (JacksonViewBean)
				processor.resolveArgument(methodParameter, this.container, this.request, this.factory);

		assertNotNull(result);
		assertEquals("with", result.getWithView1());
		assertNull(result.getWithView2());
		assertNull(result.getWithoutView());
	}
