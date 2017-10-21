	@Test
	public void marshalAWrappedObjectHoldingAnXmlElementDeclElement() throws Exception {
		marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan("org.springframework.oxm.jaxb");
		marshaller.afterPropertiesSet();
		Airplane airplane = new Airplane();
		airplane.setName("test");
		StringWriter writer = new StringWriter();
		Result result = new StreamResult(writer);
		marshaller.marshal(airplane, result);
		DifferenceEvaluator ev = chain(Default, downgradeDifferencesToEqual(XML_STANDALONE));
		assertThat("Marshalling should use root Element",
				writer.toString(),
				isSimilarTo("<airplane><name>test</name></airplane>").withDifferenceEvaluator(ev));
	}
