	@Test
	@TestForIssue(jiraKey = "")
	public void manyToManyTest() throws Exception {
		createSchema( new Class[] {Project.class, Employee.class} );

/**/
/**/
/**/
/**/
/**/
		checkAlterTableStatement( new AlterTableStatement(
				ssr,
				"EMPLOYEE_PROJECT",
				"FK_EMPLOYEE",
				"EMPLOYEE_ID",
				"EMPLOYEE"
		) );
		checkAlterTableStatement( new AlterTableStatement(
				ssr,
				"EMPLOYEE_PROJECT",
				"FK_PROJECT",
				"PROJECT_ID",
				"PROJECT"
		) );
	}
