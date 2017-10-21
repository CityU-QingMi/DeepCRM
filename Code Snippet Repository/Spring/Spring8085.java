	@Test
	@Override
	@SuppressWarnings("")
	public void unmarshalPartialStaxSourceXmlStreamReader() throws Exception {
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		XMLStreamReader streamReader = inputFactory.createXMLStreamReader(new StringReader(INPUT_STRING));
		streamReader.nextTag(); // skip to flights
		streamReader.nextTag(); // skip to flight
		Source source = StaxUtils.createStaxSource(streamReader);
		JAXBElement<FlightType> element = (JAXBElement<FlightType>) unmarshaller.unmarshal(source);
		FlightType flight = element.getValue();
		testFlight(flight);
	}
