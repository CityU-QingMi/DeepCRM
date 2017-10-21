	@Test
	public void dropCatalog_unfiltered() {
		RecordingTarget target = doDrop( new DefaultSchemaFilter() );

		Assert.assertThat( target.getActions( TABLE_DROP ), containsExactly(
				"the_entity_0",
				"the_catalog_1.the_entity_1",
				"the_catalog_1.the_entity_2",
				"the_catalog_2.the_entity_3",
				"the_catalog_2.the_entity_4"
		) );
	}
