	@SuppressWarnings("")
	private Schema loadSchema(Resource[] resources, String schemaLanguage) throws IOException, SAXException {
		if (logger.isDebugEnabled()) {
			logger.debug("Setting validation schema to " +
					StringUtils.arrayToCommaDelimitedString(this.schemaResources));
		}
		Assert.notEmpty(resources, "No resources given");
		Assert.hasLength(schemaLanguage, "No schema language provided");
		Source[] schemaSources = new Source[resources.length];
		XMLReader xmlReader = org.xml.sax.helpers.XMLReaderFactory.createXMLReader();
		xmlReader.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
		for (int i = 0; i < resources.length; i++) {
			Assert.notNull(resources[i], "Resource is null");
			Assert.isTrue(resources[i].exists(), "Resource " + resources[i] + " does not exist");
			InputSource inputSource = SaxResourceUtils.createInputSource(resources[i]);
			schemaSources[i] = new SAXSource(xmlReader, inputSource);
		}
		SchemaFactory schemaFactory = SchemaFactory.newInstance(schemaLanguage);
		if (this.schemaResourceResolver != null) {
			schemaFactory.setResourceResolver(this.schemaResourceResolver);
		}
		return schemaFactory.newSchema(schemaSources);
	}
