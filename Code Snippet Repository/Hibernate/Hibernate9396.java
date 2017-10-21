	@Test
	@TestForIssue(jiraKey = "")
	public void testUniqueConstraintIsGenerated() throws Exception {
		new SchemaExport()
				.setOutputFile( output.getAbsolutePath() )
				.create( EnumSet.of( TargetType.SCRIPT ), metadata );

		if ( getDialect() instanceof DB2Dialect) {
			assertThat(
					"The test_entity_item table unique constraint has not been generated",
					isCreateUniqueIndexGenerated("test_entity_item", "item"),
					is(true)
			);
		}
		else {
			assertThat(
					"The test_entity_item table unique constraint has not been generated",
					isUniqueConstraintGenerated("test_entity_item", "item"),
					is(true)
			);
		}

		assertThat(
				"The test_entity_children table unique constraint has not been generated",
				isUniqueConstraintGenerated( "test_entity_children", "child" ),
				is( true )
		);
	}
