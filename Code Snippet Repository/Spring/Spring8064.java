	@Test
	public void unmarshalDomSource() throws Exception {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = builder.newDocument();
		Element flightsElement = document.createElementNS("http://samples.springframework.org/flight", "tns:flights");
		document.appendChild(flightsElement);
		Element flightElement = document.createElementNS("http://samples.springframework.org/flight", "tns:flight");
		flightsElement.appendChild(flightElement);
		Element numberElement = document.createElementNS("http://samples.springframework.org/flight", "tns:number");
		flightElement.appendChild(numberElement);
		Text text = document.createTextNode("42");
		numberElement.appendChild(text);
		DOMSource source = new DOMSource(document);
		Object flights = unmarshaller.unmarshal(source);
		testFlights(flights);
	}
