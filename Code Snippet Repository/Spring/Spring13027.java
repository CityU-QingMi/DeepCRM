	@Test
	public void resolveHttpEntityArgumentWithJacksonJsonViewAndXmlMessageConverter() throws Exception {
		String content = "<root><withView1>with</withView1><withView2>with</withView2><withoutView>without</withoutView></root>";
		this.servletRequest.setContent(content.getBytes("UTF-8"));
		this.servletRequest.setContentType(MediaType.APPLICATION_XML_VALUE);

		Method method = JacksonController.class.getMethod("handleHttpEntity", HttpEntity.class);
		HandlerMethod handlerMethod = new HandlerMethod(new JacksonController(), method);
		MethodParameter methodParameter = handlerMethod.getMethodParameters()[0];

		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(new MappingJackson2XmlHttpMessageConverter());

		HttpEntityMethodProcessor processor = new HttpEntityMethodProcessor(
				converters, null, Collections.singletonList(new JsonViewRequestBodyAdvice()));

		@SuppressWarnings("unchecked")
		HttpEntity<JacksonViewBean> result = (HttpEntity<JacksonViewBean>)
				processor.resolveArgument(methodParameter, this.container, this.request, this.factory);

		assertNotNull(result);
		assertNotNull(result.getBody());
		assertEquals("with", result.getBody().getWithView1());
		assertNull(result.getBody().getWithView2());
		assertNull(result.getBody().getWithoutView());
	}
