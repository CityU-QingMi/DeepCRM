	@Test
	public void annotatedMarshalStreamResultWriter() throws Exception {
		marshaller.setAnnotatedClasses(Flight.class);
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		Flight flight = new Flight();
		flight.setFlightNumber(42);
		marshaller.marshal(flight, result);
		String expected = "<flight><number>42</number></flight>";
		assertThat("Marshaller writes invalid StreamResult", writer.toString(), isSimilarTo(expected));
	}
