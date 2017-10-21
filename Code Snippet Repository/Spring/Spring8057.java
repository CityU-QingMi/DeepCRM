	public void marshalOutputStream(Object graph, OutputStream outputStream, @Nullable DataHolder dataHolder)
			throws XmlMappingException, IOException {

		if (this.streamDriver != null) {
			doMarshal(graph, this.streamDriver.createWriter(outputStream), dataHolder);
		}
		else {
			marshalWriter(graph, new OutputStreamWriter(outputStream, this.encoding), dataHolder);
		}
	}
