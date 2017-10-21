	@Test
	public void testSqlFunctionAsAlias() throws Exception {
		String functionName = locateAppropriateDialectFunctionNameForAliasTest();
		if (functionName == null) {
            log.info("Dialect does not list any no-arg functions");
			return;
		}

        log.info("Using function named [" + functionName + "] for 'function as alias' test");
		String query = "select " + functionName + " from Simple as " + functionName + " where " + functionName + ".id = 10";

		Session s = openSession();
		Transaction t = s.beginTransaction();
		Simple simple = new Simple( Long.valueOf(10) );
		simple.setName("Simple 1");
		s.save( simple );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		List result = s.createQuery( query ).list();
		assertTrue( result.size() == 1 );
		assertTrue(result.get(0) instanceof Simple);
		s.delete( result.get(0) );
		t.commit();
		s.close();
	}
