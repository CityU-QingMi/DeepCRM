	@Test
	public void clearCollectionsTrue() throws Exception {
		Flights flights = new Flights();
		flights.setFlight(new Flight[]{new Flight()});
		unmarshaller.setRootObject(flights);
		unmarshaller.setClearCollections(true);
		Object result = unmarshalFlights();

		assertSame("Result Flights is different object.", flights, result);
		assertEquals("Result Flights has incorrect number of Flight.", 1, ((Flights) result).getFlightCount());
		testFlights(result);
	}
