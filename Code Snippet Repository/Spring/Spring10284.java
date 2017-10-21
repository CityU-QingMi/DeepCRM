	@Test
	public void setComplete() throws Exception {
		TestServerHttpResponse response = new TestServerHttpResponse();
		response.setComplete().block();

		assertTrue(response.statusCodeWritten);
		assertTrue(response.headersWritten);
		assertTrue(response.cookiesWritten);
		assertTrue(response.body.isEmpty());
	}
