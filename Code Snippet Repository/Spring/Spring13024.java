	@Test
	public void resolveArgumentWithJacksonJsonView() throws Exception {
		String content = "{\"withView1\" : \"with\", \"withView2\" : \"with\", \"withoutView\" : \"without\"}";
		this.servletRequest.setContent(content.getBytes("UTF-8"));
		this.servletRequest.setContentType(MediaType.APPLICATION_JSON_VALUE);

		Method method = JacksonController.class.getMethod("handleRequestBody", JacksonViewBean.class);
		HandlerMethod handlerMethod = new HandlerMethod(new JacksonController(), method);
		MethodParameter methodParameter = handlerMethod.getMethodParameters()[0];

		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(new MappingJackson2HttpMessageConverter());

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
