	@Test
	public void testDeleteOnMappedJoin() {
		TestData data = new TestData();
		data.prepare();

		Session s = openSession();
		Transaction t = s.beginTransaction();

		int count = s.createQuery( "delete Joiner where joinedName = :joinedName" ).setString( "joinedName", "joined-name" ).executeUpdate();
		assertEquals( "Incorrect deletion count on joined subclass", 1, count );

		t.commit();
		s.close();

		data.cleanup();
	}
