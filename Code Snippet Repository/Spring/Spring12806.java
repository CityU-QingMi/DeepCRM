	private void testHttpMediaTypeNotSupportedException(String url) throws Exception {
		try {
			MockHttpServletRequest request = new MockHttpServletRequest("PUT", url);
			request.setContentType("application/json");
			this.handlerMapping.getHandler(request);
			fail("HttpMediaTypeNotSupportedException expected");
		}
		catch (HttpMediaTypeNotSupportedException ex) {
			assertEquals("Invalid supported consumable media types",
					Collections.singletonList(new MediaType("application", "xml")),
					ex.getSupportedMediaTypes());
		}
	}
