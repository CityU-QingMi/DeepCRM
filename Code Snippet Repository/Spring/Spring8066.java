	@Override
	protected CastorMarshaller createMarshaller() throws Exception {
		CastorMarshaller marshaller = new CastorMarshaller();
		ClassPathResource mappingLocation = new ClassPathResource("mapping.xml", CastorMarshaller.class);
		marshaller.setMappingLocation(mappingLocation);
		Map<String, String> props = new HashMap<>(1);
		props.put(XMLProperties.SERIALIZER_FACTORY, XercesXMLSerializerFactory.class.getName());
		marshaller.setCastorProperties(props);
		marshaller.afterPropertiesSet();
		return marshaller;
	}
