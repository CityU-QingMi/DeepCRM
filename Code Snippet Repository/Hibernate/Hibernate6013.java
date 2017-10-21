	@Test
	public void testExcludeUnlistedClasses() {
		// see src/test/resources/org/hibernate/jpa/test/persistenceunit/persistence.xml
		
		final Map<String, Object> properties = new HashMap<String, Object>();
		properties.put( AvailableSettings.RESOURCES_CLASSLOADER, new TestClassLoader() );
		final List<ParsedPersistenceXmlDescriptor> parsedDescriptors = PersistenceXmlParser.locatePersistenceUnits(
				properties );
		
		doTest( parsedDescriptors, "ExcludeUnlistedClassesTest1", false );
		doTest( parsedDescriptors, "ExcludeUnlistedClassesTest2", true );
		doTest( parsedDescriptors, "ExcludeUnlistedClassesTest3", false );
		doTest( parsedDescriptors, "ExcludeUnlistedClassesTest4", true );
	}
