	private Properties getDefaultMediaTypes() {
		Properties props = new Properties();
		if (romePresent) {
			props.put("atom", MediaType.APPLICATION_ATOM_XML_VALUE);
			props.put("rss", MediaType.APPLICATION_RSS_XML_VALUE);
		}
		if (jaxb2Present || jackson2XmlPresent) {
			props.put("xml", MediaType.APPLICATION_XML_VALUE);
		}
		if (jackson2Present || gsonPresent) {
			props.put("json", MediaType.APPLICATION_JSON_VALUE);
		}
		if (jackson2SmilePresent) {
			props.put("smile", "application/x-jackson-smile");
		}
		if (jackson2CborPresent) {
			props.put("cbor", "application/cbor");
		}
		return props;
	}
