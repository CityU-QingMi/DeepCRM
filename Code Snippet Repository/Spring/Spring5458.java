	@Test
	public void parseMimeTypes() throws Exception {
		String s = "text/plain, text/html, text/x-dvi, text/x-c";
		List<MimeType> mimeTypes = MimeTypeUtils.parseMimeTypes(s);
		assertNotNull("No mime types returned", mimeTypes);
		assertEquals("Invalid amount of mime types", 4, mimeTypes.size());

		mimeTypes = MimeTypeUtils.parseMimeTypes(null);
		assertNotNull("No mime types returned", mimeTypes);
		assertEquals("Invalid amount of mime types", 0, mimeTypes.size());
	}
