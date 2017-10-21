	@Test
	public void getResourceHttpHeader() throws Exception {
		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.head("").build());
		setPathWithinHandlerMapping(exchange, "foo.css");
		this.handler.handle(exchange).block(TIMEOUT);

		assertNull(exchange.getResponse().getStatusCode());
		HttpHeaders headers = exchange.getResponse().getHeaders();
		assertEquals(MediaType.parseMediaType("text/css"), headers.getContentType());
		assertEquals(17, headers.getContentLength());
		assertEquals("max-age=3600", headers.getCacheControl());
		assertTrue(headers.containsKey("Last-Modified"));
		assertEquals(headers.getLastModified() / 1000, resourceLastModifiedDate("test/foo.css") / 1000);
		assertEquals("bytes", headers.getFirst("Accept-Ranges"));
		assertEquals(1, headers.get("Accept-Ranges").size());

		StepVerifier.create(exchange.getResponse().getBody())
				.expectErrorMatches(ex -> ex.getMessage().startsWith("No content was written"))
				.verify();
	}
