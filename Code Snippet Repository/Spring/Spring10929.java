	@Test
	public void orderedWebExceptionHandlerBeans() throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(OrderedExceptionHandlerBeanConfig.class);
		context.refresh();

		HttpHandler httpHandler = WebHttpHandlerBuilder.applicationContext(context).build();

		MockServerHttpRequest request = MockServerHttpRequest.get("/").build();
		MockServerHttpResponse response = new MockServerHttpResponse();
		httpHandler.handle(request, response).block(ofMillis(5000));

		assertEquals("ExceptionHandlerB", response.getBodyAsString().block(ofMillis(5000)));
	}
