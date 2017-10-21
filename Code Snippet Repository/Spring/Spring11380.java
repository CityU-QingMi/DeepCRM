	@Test
	public void resolveMediaTypes() throws Exception {
		String header = "text/plain; q=0.5, text/html, text/x-dvi; q=0.8, text/x-c";
		MockServerHttpRequest request = MockServerHttpRequest.get("/").header("accept", header).build();
		List<MediaType> mediaTypes = this.resolver.resolveMediaTypes(MockServerWebExchange.from(request));

		assertEquals(4, mediaTypes.size());
		assertEquals("text/html", mediaTypes.get(0).toString());
		assertEquals("text/x-c", mediaTypes.get(1).toString());
		assertEquals("text/x-dvi;q=0.8", mediaTypes.get(2).toString());
		assertEquals("text/plain;q=0.5", mediaTypes.get(3).toString());
	}
