	@Test
	public void encodeAlreadyEncodedURI() throws Exception {
		PropertyEditor uriEditor = new URIEditor(false);
		uriEditor.setAsText("http://example.com/spaces%20and%20%E2%82%AC");
		Object value = uriEditor.getValue();
		assertTrue(value instanceof URI);
		URI uri = (URI) value;
		assertEquals(uri.toString(), uriEditor.getAsText());
		assertEquals("http://example.com/spaces%20and%20%E2%82%AC", uri.toASCIIString());
	}
