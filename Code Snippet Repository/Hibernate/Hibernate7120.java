	@Test
	public void testMashallAttributeWithNullGenerationTiming()
			throws Exception {
		JaxbHbmHibernateMapping hm = new JaxbHbmHibernateMapping();
		JaxbHbmRootEntityType clazz = new JaxbHbmRootEntityType();
		JaxbHbmSimpleIdType id = new JaxbHbmSimpleIdType();
		JaxbHbmBasicAttributeType att = new JaxbHbmBasicAttributeType();
		att.setName( "attributeName" );
		clazz.getAttributes().add( att );
		clazz.setId( id );
		hm.getClazz().add( clazz );

		XmlBindingChecker.checkValidGeneration( hm );
	}
