	@Test
	public void writeUtf16() throws IOException {
		MediaType contentType = new MediaType("text", "plain", StandardCharsets.UTF_16);
		this.converter.write(Integer.valueOf(958), contentType, this.response);

		assertEquals("UTF-16", this.servletResponse.getCharacterEncoding());
		assertTrue(this.servletResponse.getContentType().startsWith(MediaType.TEXT_PLAIN_VALUE));
		assertEquals(8, this.servletResponse.getContentLength());
		// First two bytes: byte order mark
		assertArrayEquals(new byte[] { -2, -1, 0, '9', 0, '5', 0, '8' }, this.servletResponse.getContentAsByteArray());
	}
