	@Test
	public void createCatalog_unfiltered() {
		RecordingTarget target = doCreation( new DefaultSchemaFilter() );

		Assert.assertThat( target.getActions( TABLE_CREATE ), containsExactly(
				"the_entity_0",
				"the_catalog_1.the_entity_1",
				"the_catalog_1.the_entity_2",
				"the_catalog_2.the_entity_3",
				"the_catalog_2.the_entity_4"
		) );
	}
