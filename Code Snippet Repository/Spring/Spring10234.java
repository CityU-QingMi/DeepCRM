	@Test
	public void readStAXSource() throws Exception {
		MockHttpInputMessage inputMessage = new MockHttpInputMessage(BODY.getBytes("UTF-8"));
		inputMessage.getHeaders().setContentType(new MediaType("application", "xml"));
		StAXSource result = (StAXSource) converter.read(StAXSource.class, inputMessage);
		XMLStreamReader streamReader = result.getXMLStreamReader();
		assertTrue(streamReader.hasNext());
		streamReader.nextTag();
		String s = streamReader.getLocalName();
		assertEquals("root", s);
		s = streamReader.getElementText();
		assertEquals("Hello World", s);
		streamReader.close();
	}
