	@Test
	@TestForIssue(jiraKey = "")
	public void oneToManyWithJoinTableTest() throws Exception {
		createSchema( new Class[] {Person.class, Phone.class} );

/**/
/**/
/**/
/**/
/**/
		checkAlterTableStatement( new AlterTableStatement(
				ssr,
				"PERSON_PHONE",
				"PERSON_ID_FK",
				"PERSON_ID",
				"PERSON"
		) );
		checkAlterTableStatement( new AlterTableStatement(
				ssr,
				"PERSON_PHONE",
				"PHONE_ID_FK",
				"PHONE_ID",
				"PHONE"
		) );
	}
