	@Test
	public void testNonExistentOrmVersion() {
		try {
			new MetadataSources()
					.addResource( "org/hibernate/test/annotations/xml/ejb3/orm5.xml" )
					.buildMetadata();
			fail( "Expecting failure due to unsupported xsd version" );
		}
		catch ( InvalidMappingException expected ) {
		}
		catch ( UnsupportedOrmXsdVersionException expected ) {
		}
	}
