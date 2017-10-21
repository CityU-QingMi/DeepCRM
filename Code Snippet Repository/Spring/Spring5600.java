	@Test
	public void write() throws Exception {
		streamWriter.writeStartDocument();
		streamWriter.writeProcessingInstruction("pi", "content");
		streamWriter.writeStartElement("namespace", "root");
		streamWriter.writeDefaultNamespace("namespace");
		streamWriter.writeStartElement("prefix", "child", "namespace2");
		streamWriter.writeNamespace("prefix", "namespace2");
		streamWriter.writeComment("comment");
		streamWriter.writeCharacters("content");
		streamWriter.writeEndElement();
		streamWriter.writeEndElement();
		streamWriter.writeEndDocument();

		Predicate<Node> nodeFilter = n -> n.getNodeType() != Node.DOCUMENT_TYPE_NODE && n.getNodeType() != Node.PROCESSING_INSTRUCTION_NODE;
		assertThat(stringWriter.toString(), isSimilarTo(XML).withNodeFilter(nodeFilter));
	}
