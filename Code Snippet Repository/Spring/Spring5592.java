	@Test
	public void streamReaderSourceToStreamResult() throws Exception {
		XMLStreamReader streamReader = inputFactory.createXMLStreamReader(new StringReader(XML));
		StaxSource source = new StaxSource(streamReader);
		assertEquals("Invalid streamReader returned", streamReader, source.getXMLStreamReader());
		assertNull("EventReader returned", source.getXMLEventReader());
		StringWriter writer = new StringWriter();
		transformer.transform(source, new StreamResult(writer));
		assertThat("Invalid result", writer.toString(), isSimilarTo(XML));
	}
