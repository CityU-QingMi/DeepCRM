	@Test
	public void streamWriterSource() throws Exception {
		StringWriter stringWriter = new StringWriter();
		XMLStreamWriter streamWriter = inputFactory.createXMLStreamWriter(stringWriter);
		Reader reader = new StringReader(XML);
		Source source = new StreamSource(reader);
		StaxResult result = new StaxResult(streamWriter);
		assertEquals("Invalid streamWriter returned", streamWriter, result.getXMLStreamWriter());
		assertNull("EventWriter returned", result.getXMLEventWriter());
		transformer.transform(source, result);
		assertThat("Invalid result", stringWriter.toString(), isSimilarTo(XML));
	}
