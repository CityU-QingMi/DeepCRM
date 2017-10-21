	@Test
	public void writeWithError() throws Exception {
		TestServerHttpResponse response = new TestServerHttpResponse();
		IllegalStateException error = new IllegalStateException("boo");
		response.writeWith(Flux.error(error)).onErrorResume(ex -> Mono.empty()).block();

		assertFalse(response.statusCodeWritten);
		assertFalse(response.headersWritten);
		assertFalse(response.cookiesWritten);
		assertTrue(response.body.isEmpty());
	}
