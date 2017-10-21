	@Test
	public void unmarshalStreamSourceInputStreamUsingNonDefaultEncoding() throws Exception {
		String encoding = "ISO-8859-1";
		unmarshaller.setEncoding(encoding);

		StreamSource source = new StreamSource(new ByteArrayInputStream(INPUT_STRING_WITH_SPECIAL_CHARACTERS.getBytes(encoding)));
		Object flights = unmarshaller.unmarshal(source);
		testFlights(flights);

		FlightType flight = ((Flights)flights).getFlight(0);
		assertEquals("Airline is invalid", "Air Libert\u00e9", flight.getAirline());
	}
