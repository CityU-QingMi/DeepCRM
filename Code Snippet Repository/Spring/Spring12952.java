	@Test
	public void responseBodyAdvice() throws Exception {
		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(new MappingJackson2HttpMessageConverter());
		this.handlerAdapter.setMessageConverters(converters);

		this.webAppContext.registerSingleton("rba", ResponseCodeSuppressingAdvice.class);
		this.webAppContext.refresh();

		this.request.addHeader("Accept", MediaType.APPLICATION_JSON_VALUE);
		this.request.setParameter("c", "callback");

		HandlerMethod handlerMethod = handlerMethod(new SimpleController(), "handleBadRequest");
		this.handlerAdapter.afterPropertiesSet();
		this.handlerAdapter.handle(this.request, this.response, handlerMethod);

		assertEquals(200, this.response.getStatus());
		assertEquals("{\"status\":400,\"message\":\"body\"}", this.response.getContentAsString());
	}
