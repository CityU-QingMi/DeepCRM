	@Test
	@TestForIssue(jiraKey = "")
	public void testUniqueConstraintIsGenerated() throws Exception {

		new IndividuallySchemaMigratorImpl( tool, DefaultSchemaFilter.INSTANCE )
				.doMigration(
						metadata,
						options,
						new TargetDescriptorImpl()
				);

		if ( getDialect() instanceof MySQLDialect ) {
			assertThat(
					"The test_entity_item table unique constraint has not been dropped",
					checkDropIndex( "test_entity_item", "item" ),
					is( true )
			);
		}
		else {
			assertThat(
					"The test_entity_item table unique constraint has not been dropped",
					checkDropConstraint( "test_entity_item", "item" ),
					is( true )
			);
		}
	}
