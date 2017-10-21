	@Test
	public void marshalDOMResult() throws Exception {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setNamespaceAware(true);
		DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
		Document result = builder.newDocument();
		DOMResult domResult = new DOMResult(result);
		marshaller.marshal(flights, domResult);
		Document expected = builder.newDocument();
		Element flightsElement = expected.createElementNS("http://samples.springframework.org/flight", "tns:flights");
		Attr namespace = expected.createAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:tns");
		namespace.setNodeValue("http://samples.springframework.org/flight");
		flightsElement.setAttributeNode(namespace);
		expected.appendChild(flightsElement);
		Element flightElement = expected.createElementNS("http://samples.springframework.org/flight", "tns:flight");
		flightsElement.appendChild(flightElement);
		Element numberElement = expected.createElementNS("http://samples.springframework.org/flight", "tns:number");
		flightElement.appendChild(numberElement);
		Text text = expected.createTextNode("42");
		numberElement.appendChild(text);
		assertThat("Marshaller writes invalid DOMResult", result, isSimilarTo(expected));
	}
