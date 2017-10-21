	private void assertXpathEvaluatesTo(String msg, String expected, String xpath, String xmlDoc) throws Exception {
		Map<String, String> namespaces = new HashMap<>();
		namespaces.put("tns", "http://samples.springframework.org/flight");
		namespaces.put("xsi", "http://www.w3.org/2001/XMLSchema-instance");

		JAXPXPathEngine engine = new JAXPXPathEngine();
		engine.setNamespaceContext(namespaces);

		Source source = Input.fromString(xmlDoc).build();
		Iterable<Node> nodeList = engine.selectNodes(xpath, source);
		assertEquals(msg, expected, nodeList.iterator().next().getNodeValue());
	}
