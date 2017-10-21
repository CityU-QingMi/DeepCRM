	@Test
	public void testSelectDialectFunction() throws Exception {
		// From SQLFunctionsTest.testDialectSQLFunctions...
		if ( getDialect() instanceof HSQLDialect ) {
			assertTranslation( "select mod(s.count, 2) from org.hibernate.test.legacy.Simple as s where s.id = 10" );
			//assertTranslation( "from org.hibernate.test.legacy.Simple as s where mod(s.count, 2) = 0" );
		}
		assertTranslation( "select upper(human.name.first) from Human human" );
		assertTranslation( "from Human human where lower(human.name.first) like 'gav%'" );
		assertTranslation( "select upper(a.description) from Animal a" );
		assertTranslation( "select max(a.bodyWeight) from Animal a" );
	}
