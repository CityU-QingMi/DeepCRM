	@SuppressWarnings("")
	public <T extends ObjectMapper> T build() {
		ObjectMapper mapper;
		if (this.createXmlMapper) {
			mapper = (this.defaultUseWrapper != null ?
					new XmlObjectMapperInitializer().create(this.defaultUseWrapper) :
					new XmlObjectMapperInitializer().create());
		}
		else {
			mapper = (this.factory != null ? new ObjectMapper(this.factory) : new ObjectMapper());
		}
		configure(mapper);
		return (T) mapper;
	}
