	@Test
	public void resolveArgumentTypeVariable() throws Exception {
		Method method = MySimpleParameterizedController.class.getMethod("handleDto", HttpEntity.class);
		HandlerMethod handlerMethod = new HandlerMethod(new MySimpleParameterizedController(), method);
		MethodParameter methodParam = handlerMethod.getMethodParameters()[0];

		String content = "{\"name\" : \"Jad\"}";
		this.servletRequest.setContent(content.getBytes("UTF-8"));
		this.servletRequest.setContentType(MediaType.APPLICATION_JSON_VALUE);

		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(new MappingJackson2HttpMessageConverter());
		HttpEntityMethodProcessor processor = new HttpEntityMethodProcessor(converters);

		@SuppressWarnings("unchecked")
		HttpEntity<SimpleBean> result = (HttpEntity<SimpleBean>)
				processor.resolveArgument(methodParam, mavContainer, webRequest, binderFactory);

		assertNotNull(result);
		assertEquals("Jad", result.getBody().getName());
	}
