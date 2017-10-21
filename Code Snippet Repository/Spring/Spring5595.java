	@Test
	public void eventReaderSourceToDOMResult() throws Exception {
		XMLEventReader eventReader = inputFactory.createXMLEventReader(new StringReader(XML));
		StaxSource source = new StaxSource(eventReader);
		assertEquals("Invalid eventReader returned", eventReader, source.getXMLEventReader());
		assertNull("StreamReader returned", source.getXMLStreamReader());

		Document expected = documentBuilder.parse(new InputSource(new StringReader(XML)));
		Document result = documentBuilder.newDocument();
		transformer.transform(source, new DOMResult(result));
		assertThat("Invalid result", result, isSimilarTo(expected));
	}
