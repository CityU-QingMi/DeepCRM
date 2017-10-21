	@Test
	@TestForIssue(jiraKey = "")
	public void oneToManyTest() throws Exception {
		createSchema( new Class[] {User.class, UserSetting.class, Group.class} );

/**/
/**/
/**/
/**/
		checkAlterTableStatement( new AlterTableStatement(
				ssr,
				"GROUP",
				"FK_USER_GROUP",
				"USER_ID",
				"USERS"
		) );
	}
