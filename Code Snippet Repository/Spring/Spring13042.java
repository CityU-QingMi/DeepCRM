	@Test
	public void resolveParameterizedWithTypeVariableArgument() throws Exception {
		Method method = MyParameterizedControllerWithList.class.getMethod("handleDto", List.class);
		HandlerMethod handlerMethod = new HandlerMethod(new MySimpleParameterizedControllerWithList(), method);
		MethodParameter methodParam = handlerMethod.getMethodParameters()[0];

		String content = "[{\"name\" : \"Jad\"}, {\"name\" : \"Robert\"}]";
		this.servletRequest.setContent(content.getBytes("UTF-8"));
		this.servletRequest.setContentType(MediaType.APPLICATION_JSON_VALUE);

		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(new MappingJackson2HttpMessageConverter());
		RequestResponseBodyMethodProcessor processor = new RequestResponseBodyMethodProcessor(converters);

		@SuppressWarnings("unchecked")
		List<SimpleBean> result = (List<SimpleBean>) processor.resolveArgument(
				methodParam, container, request, factory);

		assertNotNull(result);
		assertEquals("Jad", result.get(0).getName());
		assertEquals("Robert", result.get(1).getName());
	}
