	@Test
	public void testImportFile() throws Exception {
		Session s = openSession();
		final Transaction tx = s.beginTransaction();

		BigInteger count = (BigInteger) s.createSQLQuery( "SELECT COUNT(*) FROM test_data" ).uniqueResult();
		assertEquals( "Incorrect row number", 3L, count.longValue() );

		final String multiLineText = (String) s.createSQLQuery( "SELECT text FROM test_data WHERE id = 2" )
				.uniqueResult();
		//  "Multi-line comment line 1\n-- line 2'\n/* line 3 */"
		final String expected = String.format( "Multi-line comment line 1%n-- line 2'%n/* line 3 */" );
		assertEquals( "Multi-line string inserted incorrectly", expected, multiLineText );

		String empty = (String) s.createSQLQuery( "SELECT text FROM test_data WHERE id = 3" ).uniqueResult();
		assertNull( "NULL value inserted incorrectly", empty );

		tx.commit();
		s.close();
	}
