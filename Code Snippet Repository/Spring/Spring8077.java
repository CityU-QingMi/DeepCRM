	private void testSupports() throws Exception {
		assertTrue("Jaxb2Marshaller does not support Flights class", marshaller.supports(Flights.class));
		assertTrue("Jaxb2Marshaller does not support Flights generic type", marshaller.supports((Type)Flights.class));

		assertFalse("Jaxb2Marshaller supports FlightType class", marshaller.supports(FlightType.class));
		assertFalse("Jaxb2Marshaller supports FlightType type", marshaller.supports((Type)FlightType.class));

		Method method = ObjectFactory.class.getDeclaredMethod("createFlight", FlightType.class);
		assertTrue("Jaxb2Marshaller does not support JAXBElement<FlightsType>",
				marshaller.supports(method.getGenericReturnType()));

		marshaller.setSupportJaxbElementClass(true);
		JAXBElement<FlightType> flightTypeJAXBElement = new JAXBElement<>(new QName("http://springframework.org", "flight"), FlightType.class,
				new FlightType());
		assertTrue("Jaxb2Marshaller does not support JAXBElement<FlightsType>", marshaller.supports(flightTypeJAXBElement.getClass()));

		assertFalse("Jaxb2Marshaller supports class not in context path", marshaller.supports(DummyRootElement.class));
		assertFalse("Jaxb2Marshaller supports type not in context path", marshaller.supports((Type)DummyRootElement.class));
		method = getClass().getDeclaredMethod("createDummyRootElement");
		assertFalse("Jaxb2Marshaller supports JAXBElement not in context path",
				marshaller.supports(method.getGenericReturnType()));

		assertFalse("Jaxb2Marshaller supports class not in context path", marshaller.supports(DummyType.class));
		assertFalse("Jaxb2Marshaller supports type not in context path", marshaller.supports((Type)DummyType.class));
		method = getClass().getDeclaredMethod("createDummyType");
		assertFalse("Jaxb2Marshaller supports JAXBElement not in context path",
				marshaller.supports(method.getGenericReturnType()));

		testSupportsPrimitives();
		testSupportsStandardClasses();
	}
