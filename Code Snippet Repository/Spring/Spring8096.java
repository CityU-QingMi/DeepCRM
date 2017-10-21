	@Test
	@SuppressWarnings({ "", "" })
	public void implicitCollections() throws Exception {
		Flights flights = new Flights();
		flights.getFlights().add(flight);
		flights.getStrings().add("42");

		Map<String, Class<?>> aliases = new HashMap<>();
		aliases.put("flight", Flight.class);
		aliases.put("flights", Flights.class);
		marshaller.setAliases(aliases);

		Map implicitCollections = Collections.singletonMap(Flights.class, "flights,strings");
		marshaller.setImplicitCollections(implicitCollections);

		Writer writer = new StringWriter();
		marshaller.marshal(flights, new StreamResult(writer));
		String result = writer.toString();
		assertXpathNotExists("/flights/flights", result);
		assertXpathExists("/flights/flight", result);
		assertXpathNotExists("/flights/strings", result);
		assertXpathExists("/flights/string", result);
	}
