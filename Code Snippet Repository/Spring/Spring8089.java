	@Test
	public void marshalDOMResult() throws Exception {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
		Document document = builder.newDocument();
		DOMResult domResult = new DOMResult(document);
		marshaller.marshal(flight, domResult);
		Document expected = builder.newDocument();
		Element flightElement = expected.createElement("flight");
		expected.appendChild(flightElement);
		Element numberElement = expected.createElement("flightNumber");
		flightElement.appendChild(numberElement);
		Text text = expected.createTextNode("42");
		numberElement.appendChild(text);
		assertThat("Marshaller writes invalid DOMResult", document, isSimilarTo(expected));
	}
