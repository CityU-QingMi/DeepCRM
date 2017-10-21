	@Test
	public void encodeURI() throws Exception {
		PropertyEditor uriEditor = new URIEditor();
		uriEditor.setAsText("http://example.com/spaces and \u20AC");
		Object value = uriEditor.getValue();
		assertTrue(value instanceof URI);
		URI uri = (URI) value;
		assertEquals(uri.toString(), uriEditor.getAsText());
		assertEquals("http://example.com/spaces%20and%20%E2%82%AC", uri.toASCIIString());
	}
