	@Test
	public void testNativeQueryWithFormulaAttribute() {
		SQLFunction dateFunction = getDialect().getFunctions().get( "current_date" );
		String dateFunctionRendered = dateFunction.render(
				null,
				java.util.Collections.EMPTY_LIST,
				sessionFactory()
		);

		String sql = String.format(
				"select t.TABLE_NAME as {t.tableName}, %s as {t.daysOld} from ALL_TABLES t  where t.TABLE_NAME = 'AUDIT_ACTIONS' ",
				dateFunctionRendered
		);
		String sql2 = String.format(
				"select TABLE_NAME as t_name, %s as t_time from ALL_TABLES   where TABLE_NAME = 'AUDIT_ACTIONS' ",
				dateFunctionRendered
		);


		Session s = openSession();
		s.beginTransaction();
		s.createSQLQuery( sql ).addEntity( "t", AllTables.class ).list();
		s.createSQLQuery( sql2 ).setResultSetMapping( "all" ).list();
		SQLQuery q = s.createSQLQuery( sql2 );
		q.addRoot( "t", AllTables.class ).addProperty( "tableName", "t_name" ).addProperty( "daysOld", "t_time" );
		q.list();
		s.getTransaction().commit();
		s.close();
	}
