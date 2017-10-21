	@Test
	public void testAccessOnPersistenceUnitDefaultsXmlElement() throws Exception {
		Class<?> classUnderTest = Tourist.class;
		List<Class<?>> classes = new ArrayList<Class<?>>();
		classes.add( classUnderTest );
		List<String> configFiles = Collections.emptyList();
		SessionFactoryImplementor factory = buildSessionFactory( classes, configFiles );

		// without any xml configuration we have field access
		assertAccessType( factory, classUnderTest, AccessType.FIELD );
		factory.close();
		// now with an additional xml configuration file changing the default access type for Tourist using persitence unit defaults
		configFiles = new ArrayList<String>();
		configFiles.add( "org/hibernate/test/annotations/access/xml/Tourist2.xml" );
		factory = buildSessionFactory( classes, configFiles );
		assertAccessType( factory, classUnderTest, AccessType.PROPERTY );
		factory.close();
	}
