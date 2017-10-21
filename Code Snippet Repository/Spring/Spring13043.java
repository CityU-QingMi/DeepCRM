	@Test
	public void resolveArgumentTypeVariableWithNonGenericConverter() throws Exception {
		Method method = MyParameterizedController.class.getMethod("handleDto", Identifiable.class);
		HandlerMethod handlerMethod = new HandlerMethod(new MySimpleParameterizedController(), method);
		MethodParameter methodParam = handlerMethod.getMethodParameters()[0];

		String content = "{\"name\" : \"Jad\"}";
		this.servletRequest.setContent(content.getBytes("UTF-8"));
		this.servletRequest.setContentType(MediaType.APPLICATION_JSON_VALUE);

		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		HttpMessageConverter target = new MappingJackson2HttpMessageConverter();
		HttpMessageConverter proxy = ProxyFactory.getProxy(HttpMessageConverter.class, new SingletonTargetSource(target));
		converters.add(proxy);
		RequestResponseBodyMethodProcessor processor = new RequestResponseBodyMethodProcessor(converters);

		SimpleBean result = (SimpleBean) processor.resolveArgument(methodParam, container, request, factory);

		assertNotNull(result);
		assertEquals("Jad", result.getName());
	}
