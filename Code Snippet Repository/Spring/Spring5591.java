	@Test
	public void eventWriterSource() throws Exception {
		StringWriter stringWriter = new StringWriter();
		XMLEventWriter eventWriter = inputFactory.createXMLEventWriter(stringWriter);
		Reader reader = new StringReader(XML);
		Source source = new StreamSource(reader);
		StaxResult result = new StaxResult(eventWriter);
		assertEquals("Invalid eventWriter returned", eventWriter, result.getXMLEventWriter());
		assertNull("StreamWriter returned", result.getXMLStreamWriter());
		transformer.transform(source, result);
		assertThat("Invalid result", stringWriter.toString(), isSimilarTo(XML));
	}
