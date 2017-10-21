	@Test
	public void unmarshalPartialStaxSourceXmlStreamReader() throws Exception {
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		XMLStreamReader streamReader = inputFactory.createXMLStreamReader(new StringReader(INPUT_STRING));
		streamReader.nextTag(); // skip to flights
		assertEquals("Invalid element", new QName("http://samples.springframework.org/flight", "flights"),
				streamReader.getName());
		streamReader.nextTag(); // skip to flight
		assertEquals("Invalid element", new QName("http://samples.springframework.org/flight", "flight"),
				streamReader.getName());
		Source source = StaxUtils.createStaxSource(streamReader);
		Object flight = unmarshaller.unmarshal(source);
		testFlight(flight);
	}
