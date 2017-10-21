	@Test
	public void testForeignKeyHasCorrectName() throws Exception {
		createSchema( new Class[] {Role.class, User.class, Person.class} );
		checkAlterTableStatement( new AlterTableStatement(
				ssr, "User",
				"FK_PERSON_ROLE",
				"USER_ID",
				"PersonRole"
		) );
	}
