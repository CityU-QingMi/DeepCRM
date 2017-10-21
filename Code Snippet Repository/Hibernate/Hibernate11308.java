	@Test
	public void testCreatedAuditTable() {
		Set<String> expectedColumns = TestTools.makeSet(
				"baby",
				"child",
				"parent",
				"relation_id",
				"grandparent",
				"id"
		);
		Set<String> unexpectedColumns = TestTools.makeSet( "notAudited" );

		Table table = metadata().getEntityBinding(
				"org.hibernate.envers.test.integration.superclass.auditparents.BabyCompleteEntity_AUD"
		).getTable();

		for ( String columnName : expectedColumns ) {
			// Check whether expected column exists.
			Assert.assertNotNull( table.getColumn( new Column( columnName ) ) );
		}
		for ( String columnName : unexpectedColumns ) {
			// Check whether unexpected column does not exist.
			Assert.assertNull( table.getColumn( new Column( columnName ) ) );
		}
	}
