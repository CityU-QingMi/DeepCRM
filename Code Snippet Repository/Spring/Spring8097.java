	@Test
	public void jettisonDriver() throws Exception {
		marshaller.setStreamDriver(new JettisonMappedXmlDriver());
		Writer writer = new StringWriter();
		marshaller.marshal(flight, new StreamResult(writer));
		assertEquals("Invalid result", "{\"flight\":{\"flightNumber\":42}}", writer.toString());
		Object o = marshaller.unmarshal(new StreamSource(new StringReader(writer.toString())));
		assertTrue("Unmarshalled object is not Flights", o instanceof Flight);
		Flight unflight = (Flight) o;
		assertNotNull("Flight is null", unflight);
		assertEquals("Number is invalid", 42L, unflight.getFlightNumber());
	}
