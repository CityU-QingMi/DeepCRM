	@Test
	@FailureExpected(jiraKey = "")
	public void testNativeQueryWithFormulaAttributeWithoutAlias() {
		String sql = "select TABLE_NAME , sysdate() from all_tables  where TABLE_NAME = 'AUDIT_ACTIONS' ";
		Session s = openSession();
		s.beginTransaction();
		s.createSQLQuery( sql ).addEntity( "t", AllTables.class ).list();
		s.getTransaction().commit();
		s.close();
	}
