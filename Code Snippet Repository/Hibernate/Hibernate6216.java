	@Test
	@TestForIssue( jiraKey = "" )
	public void testAnnotations() {
		try {
			new MetadataSources( ssr )
					.addAnnotatedClass( TheEntity.class )
					.buildMetadata();
			fail( "Expecting a failure" );
		}
		catch (MappingException e) {
			assertThat( e.getMessage(), startsWith( "HHH000474: Ambiguous persistent property methods detected on" ) );
		}
	}
