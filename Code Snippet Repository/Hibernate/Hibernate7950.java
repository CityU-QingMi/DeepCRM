	@Test
	public void testStr() {
		Session session = openSession();
		Transaction txn = session.beginTransaction();
		Animal an = new Animal();
		an.setBodyWeight(123.45f);
		session.persist( an );
		String str = (String) session.createQuery("select str(an.bodyWeight) from Animal an where str(an.bodyWeight) like '%1%'").uniqueResult();
		if ( (getDialect() instanceof DB2Dialect || getDialect() instanceof TeradataDialect) && !(getDialect() instanceof DerbyDialect) ) {
			assertTrue( str.startsWith( "1.234" ) );
		}
		else //noinspection deprecation
			if ( getDialect() instanceof SybaseDialect || getDialect() instanceof Sybase11Dialect || getDialect() instanceof SybaseASE15Dialect || getDialect() instanceof SybaseAnywhereDialect || getDialect() instanceof SQLServerDialect ) {
			// str(val) on sybase assumes a default of 10 characters with no decimal point or decimal values
			// str(val) on sybase result is right-justified
			assertEquals( str.length(), 10 );
			assertTrue( str.endsWith("123") );
			str = (String) session.createQuery("select str(an.bodyWeight, 8, 3) from Animal an where str(an.bodyWeight, 8, 3) like '%1%'").uniqueResult();
			assertEquals( str.length(), 8 );
			assertTrue( str.endsWith( "123.450" ) );
		}
		else {
			assertTrue( str.startsWith("123.4") );
		}

		//noinspection deprecation
		if ( ! ( getDialect() instanceof SybaseDialect ) && ! ( getDialect() instanceof Sybase11Dialect ) && ! ( getDialect() instanceof SybaseASE15Dialect ) && ! ( getDialect() instanceof SybaseAnywhereDialect ) && ! ( getDialect() instanceof SQLServerDialect || getDialect() instanceof TeradataDialect ) ) {
			// In TransactSQL (the variant spoken by Sybase and SQLServer), the str() function
			// is explicitly intended for numeric values only...
			String dateStr1 = (String) session.createQuery("select str(current_date) from Animal").uniqueResult();
			String dateStr2 = (String) session.createQuery("select str(year(current_date))||'-'||str(month(current_date))||'-'||str(day(current_date)) from Animal").uniqueResult();
			System.out.println(dateStr1 + '=' + dateStr2);
			if ( ! ( getDialect() instanceof Oracle8iDialect ) ) { //Oracle renders the name of the month :(
				String[] dp1 = StringHelper.split("-", dateStr1);
				String[] dp2 = StringHelper.split( "-", dateStr2 );
				for (int i=0; i<3; i++) {
					if ( dp1[i].startsWith( "0" ) ) {
						dp1[i] = dp1[i].substring( 1 );
					}
					assertEquals( dp1[i], dp2[i] );
				}
			}
		}
		session.delete(an);
		txn.commit();
		session.close();
	}
