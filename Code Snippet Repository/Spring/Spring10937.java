	@Test
	public void handleErrorFromFilter() throws Exception {

		MockServerHttpRequest request = MockServerHttpRequest.get("/").build();
		MockServerHttpResponse response = new MockServerHttpResponse();

		TestExceptionHandler exceptionHandler = new TestExceptionHandler();

		WebHttpHandlerBuilder.webHandler(new StubWebHandler())
				.filter(new ExceptionFilter())
				.exceptionHandler(exceptionHandler).build()
				.handle(request, response)
				.block();

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertNotNull(exceptionHandler.ex);
		assertEquals("boo", exceptionHandler.ex.getMessage());
	}
