	@Test
	public void updateRequestPath() throws Exception {

		URI uri = URI.create("http://localhost:8080/aA/bB/cC");
		RequestPath requestPath = RequestPath.parse(uri, null);

		assertEquals("", requestPath.contextPath().value());
		assertEquals("/aA/bB/cC", requestPath.pathWithinApplication().value());

		requestPath = requestPath.modifyContextPath("/aA");

		assertEquals("/aA", requestPath.contextPath().value());
		assertEquals("/bB/cC", requestPath.pathWithinApplication().value());
	}
