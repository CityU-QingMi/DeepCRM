	@Test
	public void lazyInit() throws Exception {
		marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath(CONTEXT_PATH);
		marshaller.setLazyInit(true);
		marshaller.afterPropertiesSet();
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		marshaller.marshal(flights, result);
		DifferenceEvaluator ev = chain(Default, downgradeDifferencesToEqual(XML_STANDALONE));
		assertThat("Marshaller writes invalid StreamResult", writer.toString(),
				isSimilarTo(EXPECTED_STRING).withDifferenceEvaluator(ev));
	}
