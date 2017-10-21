	@Test
	public void writeXmlRootElementSubclass() throws Exception {
		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		converter.write(rootElementCglib, null, outputMessage);
		assertEquals("Invalid content-type", new MediaType("application", "xml"),
				outputMessage.getHeaders().getContentType());
		DifferenceEvaluator ev = chain(Default, downgradeDifferencesToEqual(XML_STANDALONE));
		assertThat("Invalid result", outputMessage.getBodyAsString(StandardCharsets.UTF_8),
				isSimilarTo("<rootElement><type s=\"Hello World\"/></rootElement>").withDifferenceEvaluator(ev));
	}
