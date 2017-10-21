	@Test
	public void createSchema_filtered() {
		RecordingTarget target = doCreation( new TestSchemaFilter() );

		Assert.assertThat( target.getActions( SCHEMA_CREATE ), containsExactly( "the_schema_1" ) );
		Assert.assertThat(
				target.getActions( TABLE_CREATE ),
				containsExactly( "the_entity_0", "the_schema_1.the_entity_1" )
		);
	}
