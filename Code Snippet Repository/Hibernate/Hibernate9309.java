	@Test
	public void createSchema_unfiltered() {
		RecordingTarget target = doCreation( new DefaultSchemaFilter() );

		Assert.assertThat( target.getActions( SCHEMA_CREATE ), containsExactly( "the_schema_1", "the_schema_2" ) );
		Assert.assertThat( target.getActions( TABLE_CREATE ), containsExactly(
				"the_entity_0",
				"the_schema_1.the_entity_1",
				"the_schema_1.the_entity_2",
				"the_schema_2.the_entity_3",
				"the_schema_2.the_entity_4"
		) );
	}
