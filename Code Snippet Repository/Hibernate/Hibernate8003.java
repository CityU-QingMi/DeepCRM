	@Test
	public void testUpdateOnMammal() {
		TestData data = new TestData();
		data.prepare();

		Session s = openSession();
		Transaction t = s.beginTransaction();

		int count = s.createQuery( "update Mammal set description = description" ).executeUpdate();
		assertEquals( "incorrect update count against 'middle' of joined-subclass hierarchy", 2, count );

		count = s.createQuery( "update Mammal set bodyWeight = 25" ).executeUpdate();
		assertEquals( "incorrect update count against 'middle' of joined-subclass hierarchy", 2, count );

		if ( ! ( getDialect() instanceof MySQLDialect ) ) {
			// MySQL does not support (even un-correlated) subqueries against the update-mutating table
			count = s.createQuery( "update Mammal set bodyWeight = ( select max(bodyWeight) from Animal )" ).executeUpdate();
			assertEquals( "incorrect update count against 'middle' of joined-subclass hierarchy", 2, count );
		}

		t.commit();
		s.close();

		data.cleanup();
	}
