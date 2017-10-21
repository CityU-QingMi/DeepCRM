	@Test
	@TestForIssue( jiraKey = "")
	public void testDefaultJoinColumnOwnerPrimaryTableOverride() {
		// NOTE: expected JPA entity names are explicit here (rather than just getting them from the PersistentClass)
		//       to ensure that entity names/tables are not changed (which would invalidate these test cases).

		// Boy has @Entity @Table(name="tbl_Boys")
		// MyNamingStrategyDelegator will use the table name (instead of JPA entity name) in generated join column.
		checkDefaultJoinColumnName( Boy.class, "hatedNames", "tbl_Boys_id" );
	}
