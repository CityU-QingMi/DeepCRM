	@Test
	public void jsonDriver() throws Exception {
		marshaller.setStreamDriver(new JsonHierarchicalStreamDriver() {
			@Override
			public HierarchicalStreamWriter createWriter(Writer writer) {
				return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE,
						new JsonWriter.Format(new char[0], new char[0],
								JsonWriter.Format.SPACE_AFTER_LABEL | JsonWriter.Format.COMPACT_EMPTY_ELEMENT));
			}
		});

		Writer writer = new StringWriter();
		marshaller.marshal(flight, new StreamResult(writer));
		assertEquals("Invalid result", "{\"flightNumber\": 42}", writer.toString());
	}
