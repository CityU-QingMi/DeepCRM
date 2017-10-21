	@Test
	public void marshalDOMResultToExistentDocument() throws Exception {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
		Document existent = builder.newDocument();
		Element rootElement = existent.createElement("root");
		Element flightsElement = existent.createElement("flights");
		rootElement.appendChild(flightsElement);
		existent.appendChild(rootElement);

		// marshall into the existent document
		DOMResult domResult = new DOMResult(flightsElement);
		marshaller.marshal(flight, domResult);

		Document expected = builder.newDocument();
		Element eRootElement = expected.createElement("root");
		Element eFlightsElement = expected.createElement("flights");
		Element eFlightElement = expected.createElement("flight");
		eRootElement.appendChild(eFlightsElement);
		eFlightsElement.appendChild(eFlightElement);
		expected.appendChild(eRootElement);
		Element eNumberElement = expected.createElement("flightNumber");
		eFlightElement.appendChild(eNumberElement);
		Text text = expected.createTextNode("42");
		eNumberElement.appendChild(text);
		assertThat("Marshaller writes invalid DOMResult", existent, isSimilarTo(expected));
	}
