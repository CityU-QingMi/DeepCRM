	@Test
	@TestForIssue( jiraKey = "" )
	public void testHbmXml() {
		try {
			new MetadataSources( ssr )
					.addResource( "org/hibernate/property/TheEntity.hbm.xml" )
					.buildMetadata();
			fail( "Expecting a failure" );
		}
		catch (MappingException e) {
			assertThat( e.getMessage(), startsWith( "In trying to locate getter for property [id]" ) );
		}
	}
