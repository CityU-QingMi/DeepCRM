	@Test
	public void eventReaderSourceToStreamResult() throws Exception {
		XMLEventReader eventReader = inputFactory.createXMLEventReader(new StringReader(XML));
		StaxSource source = new StaxSource(eventReader);
		assertEquals("Invalid eventReader returned", eventReader, source.getXMLEventReader());
		assertNull("StreamReader returned", source.getXMLStreamReader());
		StringWriter writer = new StringWriter();
		transformer.transform(source, new StreamResult(writer));
		assertThat("Invalid result", writer.toString(), isSimilarTo(XML));
	}
