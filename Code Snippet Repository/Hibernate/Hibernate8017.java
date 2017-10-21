	@Test
	public void testDeleteRestrictedOnManyToOne() {
		TestData data = new TestData();
		data.prepare();

		Session s = openSession();
		Transaction t = s.beginTransaction();

		int count = s.createQuery( "delete Animal where mother = :mother" )
				.setEntity( "mother", data.butterfly )
				.executeUpdate();
		assertEquals( 1, count );

		t.commit();
		s.close();

		data.cleanup();
	}
