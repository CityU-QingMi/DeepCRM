	@Test
	public void contentTypeHeaderUTF8() {
		String contentType = "test/plain;charset=UTF-8";
		response.setHeader("Content-Type", contentType);
		assertEquals(contentType, response.getContentType());
		assertEquals(contentType, response.getHeader("Content-Type"));
		assertEquals("UTF-8", response.getCharacterEncoding());

		response = new MockHttpServletResponse();
		response.addHeader("Content-Type", contentType);
		assertEquals(contentType, response.getContentType());
		assertEquals(contentType, response.getHeader("Content-Type"));
		assertEquals("UTF-8", response.getCharacterEncoding());
	}
