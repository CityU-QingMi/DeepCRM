	public void marshalWriter(Object graph, Writer writer, @Nullable DataHolder dataHolder)
			throws XmlMappingException, IOException {

		if (this.streamDriver != null) {
			doMarshal(graph, this.streamDriver.createWriter(writer), dataHolder);
		}
		else {
			doMarshal(graph, new CompactWriter(writer), dataHolder);
		}
	}
