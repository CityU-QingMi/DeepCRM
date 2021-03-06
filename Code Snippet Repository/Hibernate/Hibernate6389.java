	@Test
	@TestForIssue( jiraKey = "")
	public void testDefaultJoinColumnOwnerEntityNameAndPKColumnOverride() {
		// NOTE: expected JPA entity names are explicit here (rather than just getting them from the PersistentClass)
		//       to ensure that entity names/tables are not changed (which would invalidate these test cases).

		// Matrix has @Entity(name="Mtx"); entity table name defaults to "Mtx"; owner PK column is configured as "mId"
		// MyNamingStrategyDelegator will use owner primary table name, which will default to the JPA entity name
		// in generated join column.
		checkDefaultJoinColumnName( Matrix.class, "mvalues", "Mtx_mId" );
	}
