	@Test
	public void testExpressionWithParamInFunction() {
		Session s = openSession();
		s.beginTransaction();
		s.createQuery( "from Animal a where abs(a.bodyWeight-:param) < 2.0" ).setLong( "param", 1 ).list();
		s.createQuery( "from Animal a where abs(:param - a.bodyWeight) < 2.0" ).setLong( "param", 1 ).list();
		if ( ( getDialect() instanceof HSQLDialect ) || ( getDialect() instanceof DB2Dialect ) ) {
			// HSQLDB and DB2 don't like the abs(? - ?) syntax. bit work if at least one parameter is typed...
			s.createQuery( "from Animal where abs(cast(:x as long) - :y) < 2.0" ).setLong( "x", 1 ).setLong( "y", 1 ).list();
			s.createQuery( "from Animal where abs(:x - cast(:y as long)) < 2.0" ).setLong( "x", 1 ).setLong( "y", 1 ).list();
			s.createQuery( "from Animal where abs(cast(:x as long) - cast(:y as long)) < 2.0" ).setLong( "x", 1 ).setLong( "y", 1 ).list();
		}
		else {
			s.createQuery( "from Animal where abs(:x - :y) < 2.0" ).setLong( "x", 1 ).setLong( "y", 1 ).list();
		}

		if ( getDialect() instanceof DB2Dialect ) {
			s.createQuery( "from Animal where lower(upper(cast(:foo as string))) like 'f%'" ).setString( "foo", "foo" ).list();
		}
		else {
			s.createQuery( "from Animal where lower(upper(:foo)) like 'f%'" ).setString( "foo", "foo" ).list();
		}
		s.createQuery( "from Animal a where abs(abs(a.bodyWeight - 1.0 + :param) * abs(length('ffobar')-3)) = 3.0" ).setLong(
				"param", 1
		).list();
		if ( getDialect() instanceof DB2Dialect ) {
			s.createQuery( "from Animal where lower(upper('foo') || upper(cast(:bar as string))) like 'f%'" ).setString( "bar", "xyz" ).list();
		}
		else {
			s.createQuery( "from Animal where lower(upper('foo') || upper(:bar)) like 'f%'" ).setString( "bar", "xyz" ).list();
		}
		
		if ( getDialect() instanceof AbstractHANADialect ) {
			s.createQuery( "from Animal where abs(cast(1 as double) - cast(:param as double)) = 1.0" )
					.setLong( "param", 1 ).list();
		}
		else if ( !( getDialect() instanceof PostgreSQLDialect || getDialect() instanceof PostgreSQL81Dialect
				|| getDialect() instanceof MySQLDialect ) ) {
			s.createQuery( "from Animal where abs(cast(1 as float) - cast(:param as float)) = 1.0" )
					.setLong( "param", 1 ).list();
		}

		s.getTransaction().commit();
		s.close();
	}
