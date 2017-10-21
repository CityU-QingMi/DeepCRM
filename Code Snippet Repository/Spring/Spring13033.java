	@Test
	public void resolveArgumentTypeVariableWithGenericInterface() throws Exception {
		this.servletRequest.setContent("\"foo\"".getBytes("UTF-8"));
		this.servletRequest.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

		Method method = MyControllerImplementingInterface.class.getMethod("handle", Object.class);
		HandlerMethod handlerMethod = new HandlerMethod(new MyControllerImplementingInterface(), method);
		MethodParameter methodParameter = handlerMethod.getMethodParameters()[0];

		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(new MappingJackson2HttpMessageConverter());

		RequestResponseBodyMethodProcessor processor = new RequestResponseBodyMethodProcessor(converters);

		String value = (String) processor.readWithMessageConverters(
				this.request, methodParameter, methodParameter.getGenericParameterType());
		assertEquals("foo", value);
	}
