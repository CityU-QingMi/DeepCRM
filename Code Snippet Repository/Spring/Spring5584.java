	@Test
	public void standard() throws Exception {
		String xml = "<foo><bar>baz</bar></foo>";
		List<XMLEvent> events = readEvents(xml);

		ListBasedXMLEventReader reader = new ListBasedXMLEventReader(events);

		StringWriter resultWriter = new StringWriter();
		XMLEventWriter writer = this.outputFactory.createXMLEventWriter(resultWriter);
		writer.add(reader);

		assertThat(resultWriter.toString(), isSimilarTo(xml));
	}
