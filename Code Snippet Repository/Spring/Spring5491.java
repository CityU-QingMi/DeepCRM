	private Properties loadProperties(String propString, boolean useReader) throws IOException {
		DefaultPropertiesPersister persister = new DefaultPropertiesPersister();
		Properties props = new Properties();
		if (useReader) {
			persister.load(props, new StringReader(propString));
		}
		else {
			persister.load(props, new ByteArrayInputStream(propString.getBytes()));
		}
		assertEquals("message1", props.getProperty("code1"));
		assertEquals("message2", props.getProperty("code2"));
		return props;
	}
