	private String storeProperties(Properties props, String header, boolean useWriter) throws IOException {
		DefaultPropertiesPersister persister = new DefaultPropertiesPersister();
		String propCopy = null;
		if (useWriter) {
			StringWriter propWriter = new StringWriter();
			persister.store(props, propWriter, header);
			propCopy = propWriter.toString();
		}
		else {
			ByteArrayOutputStream propOut = new ByteArrayOutputStream();
			persister.store(props, propOut, header);
			propCopy = new String(propOut.toByteArray());
		}
		if (header != null) {
			assertTrue(propCopy.indexOf(header) != -1);
		}
		assertTrue(propCopy.indexOf("\ncode1=message1") != -1);
		assertTrue(propCopy.indexOf("\ncode2=message2") != -1);
		return propCopy;
	}
