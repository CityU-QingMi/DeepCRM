	@Test
	@TestForIssue(jiraKey = "")
	public void oneToOneTest() throws Exception {
		createSchema( new Class[] {User.class, UserSetting.class, Group.class} );

/**/
/**/
/**/
/**/
/**/
		checkAlterTableStatement( new AlterTableStatement(
				ssr,
				"USERS",
				"FK_TO_USER_SETTING",
				"USER_SETTING_ID",
				"USER_SETTING"
		) );
		checkAlterTableStatement( new AlterTableStatement(
				ssr,
				"USER_SETTING",
				"FK_TO_USER",
				"USER_ID",
				"USERS"
		) );
	}
