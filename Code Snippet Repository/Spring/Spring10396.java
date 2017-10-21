	@Test
	public void resolveMediaTypesFromMultipleHeaderValues() throws Exception {
		this.servletRequest.addHeader("Accept", "text/plain; q=0.5, text/html");
		this.servletRequest.addHeader("Accept", "text/x-dvi; q=0.8, text/x-c");
		List<MediaType> mediaTypes = this.strategy.resolveMediaTypes(this.webRequest);

		assertEquals(4, mediaTypes.size());
		assertEquals("text/html", mediaTypes.get(0).toString());
		assertEquals("text/x-c", mediaTypes.get(1).toString());
		assertEquals("text/x-dvi;q=0.8", mediaTypes.get(2).toString());
		assertEquals("text/plain;q=0.5", mediaTypes.get(3).toString());
	}
