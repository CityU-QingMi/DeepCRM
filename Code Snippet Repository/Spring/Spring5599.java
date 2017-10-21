	@Test
	public void readCorrect() throws Exception {
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		StAXSource source = new StAXSource(streamReader);
		StringWriter writer = new StringWriter();
		transformer.transform(source, new StreamResult(writer));
		Predicate<Node> nodeFilter = n ->
				n.getNodeType() != Node.DOCUMENT_TYPE_NODE && n.getNodeType() != Node.PROCESSING_INSTRUCTION_NODE;
		assertThat(writer.toString(), isSimilarTo(XML).withNodeFilter(nodeFilter));
	}
