	@Test
	public void readStAXSourceExternal() throws Exception {
		MockHttpInputMessage inputMessage = new MockHttpInputMessage(bodyExternal.getBytes("UTF-8"));
		inputMessage.getHeaders().setContentType(new MediaType("application", "xml"));
		converter.setSupportDtd(true);
		StAXSource result = (StAXSource) converter.read(StAXSource.class, inputMessage);
		XMLStreamReader streamReader = result.getXMLStreamReader();
		assertTrue(streamReader.hasNext());
		streamReader.next();
		streamReader.next();
		String s = streamReader.getLocalName();
		assertEquals("root", s);
		try {
			s = streamReader.getElementText();
			assertNotEquals("Foo Bar", s);
		}
		catch (XMLStreamException ex) {
			// Some parsers raise a parse exception
		}
		streamReader.close();
	}
