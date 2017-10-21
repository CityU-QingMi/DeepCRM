	@Test
	public void testAccessOnElementCollectionXmlElement() throws Exception {
		Class<?> classUnderTest = Boy.class;
		List<Class<?>> classes = new ArrayList<Class<?>>();
		classes.add( classUnderTest );
		List<String> configFiles = new ArrayList<String>();
		configFiles.add( "org/hibernate/test/annotations/access/xml/Boy.xml" );
		SessionFactoryImplementor factory = buildSessionFactory( classes, configFiles );
		assertAccessType( factory, classUnderTest, AccessType.PROPERTY );
		factory.close();
	}
