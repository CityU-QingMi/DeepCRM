	@Test
	public void dropSchema_filtered() {
		RecordingTarget target = doDrop( new TestSchemaFilter() );

		Assert.assertThat( target.getActions( SCHEMA_DROP ), containsExactly( "the_schema_1" ) );
		Assert.assertThat(
				target.getActions( TABLE_DROP ),
				containsExactly( "the_entity_0", "the_schema_1.the_entity_1" )
		);
	}
