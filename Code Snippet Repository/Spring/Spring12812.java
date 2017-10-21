	@Test
	public void getHandlerTestInvalidContentType() throws Exception {
		try {
			MockHttpServletRequest request = new MockHttpServletRequest("PUT", "/person/1");
			request.setContentType("bogus");
			this.handlerMapping.getHandler(request);
			fail("HttpMediaTypeNotSupportedException expected");
		}
		catch (HttpMediaTypeNotSupportedException ex) {
			assertEquals("Invalid mime type \"bogus\": does not contain '/'", ex.getMessage());
		}
	}
