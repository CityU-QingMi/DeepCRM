	@Test
	@Ignore("")
	public void clearCollectionsFalse() throws Exception {
		Flights flights = new Flights();
		flights.setFlight(new Flight[] {new Flight(), null});
		unmarshaller.setRootObject(flights);
		unmarshaller.setClearCollections(false);
		Object result = unmarshalFlights();

		assertSame("Result Flights is different object.", flights, result);
		assertEquals("Result Flights has incorrect number of Flight.", 3, ((Flights) result).getFlightCount());
		assertNull("Flight shouldn't have number.", flights.getFlight(0).getNumber());
		assertNull("Null Flight was expected.", flights.getFlight()[1]);
		testFlight(flights.getFlight()[2]);
	}
