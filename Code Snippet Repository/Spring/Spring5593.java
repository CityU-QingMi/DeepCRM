	@Test
	public void streamReaderSourceToDOMResult() throws Exception {
		XMLStreamReader streamReader = inputFactory.createXMLStreamReader(new StringReader(XML));
		StaxSource source = new StaxSource(streamReader);
		assertEquals("Invalid streamReader returned", streamReader, source.getXMLStreamReader());
		assertNull("EventReader returned", source.getXMLEventReader());

		Document expected = documentBuilder.parse(new InputSource(new StringReader(XML)));
		Document result = documentBuilder.newDocument();
		transformer.transform(source, new DOMResult(result));
		assertThat("Invalid result", result, isSimilarTo(expected));
	}
