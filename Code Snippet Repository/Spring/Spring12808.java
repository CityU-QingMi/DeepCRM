	private void testHttpMediaTypeNotAcceptableException(String url) throws Exception {
		try {
			MockHttpServletRequest request = new MockHttpServletRequest("GET", url);
			request.addHeader("Accept", "application/json");
			this.handlerMapping.getHandler(request);
			fail("HttpMediaTypeNotAcceptableException expected");
		}
		catch (HttpMediaTypeNotAcceptableException ex) {
			assertEquals("Invalid supported producible media types",
					Collections.singletonList(new MediaType("application", "xml")),
					ex.getSupportedMediaTypes());
		}
	}
