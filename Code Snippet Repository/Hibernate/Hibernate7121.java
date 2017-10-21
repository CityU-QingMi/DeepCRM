	public static void checkValidGeneration(JaxbHbmHibernateMapping hbmMapping)
			throws Exception {
		JAXBContext jaxbContext = JAXBContext
				.newInstance( JaxbHbmHibernateMapping.class );

		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		jaxbMarshaller.marshal( hbmMapping, bos );
		ByteArrayInputStream is = new ByteArrayInputStream( bos.toByteArray() );
		ServiceRegistry sr = new StandardServiceRegistryBuilder().build();
		new XmlMappingBinderAccess( sr ).bind( is );
	}
