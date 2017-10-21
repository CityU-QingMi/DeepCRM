	@Test
	public void whitespace() throws Exception {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><test><node1> </node1><node2> Some text </node2></test>";

		Transformer transformer = TransformerFactory.newInstance().newTransformer();

		AbstractStaxXMLReader staxXmlReader = createStaxXmlReader(
				new ByteArrayInputStream(xml.getBytes("UTF-8")));

		SAXSource source = new SAXSource(staxXmlReader, new InputSource());
		DOMResult result = new DOMResult();

		transformer.transform(source, result);

		Node node1 = result.getNode().getFirstChild().getFirstChild();
		assertEquals(" ", node1.getTextContent());
		assertEquals(" Some text ", node1.getNextSibling().getTextContent());
	}
