	@Test
	@Ignore("")
	public void aliasesByTypeStringStringMap() throws Exception {
		Map<String, String> aliases = new HashMap<>();
		aliases.put("flight", Flight.class.getName());
		FlightSubclass flight = new FlightSubclass();
		flight.setFlightNumber(42);
		marshaller.setAliasesByType(aliases);

		Writer writer = new StringWriter();
		marshaller.marshal(flight, new StreamResult(writer));
		assertThat("Marshaller does not use attributes", writer.toString(), isSimilarTo(EXPECTED_STRING));
	}
