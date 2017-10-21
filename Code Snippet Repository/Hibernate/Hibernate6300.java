	@Test
	public void testAccessOnMappedSuperClassXmlElement() throws Exception {
		Class<?> classUnderTest = Waiter.class;
		List<Class<?>> classes = new ArrayList<Class<?>>();
		classes.add( classUnderTest );
		classes.add( Crew.class );
		List<String> configFiles = new ArrayList<String>();
		configFiles.add( "org/hibernate/test/annotations/access/xml/Crew.xml" );
		SessionFactoryImplementor factory = buildSessionFactory( classes, configFiles );
		assertAccessType( factory, classUnderTest, AccessType.FIELD );
		factory.close();
	}
