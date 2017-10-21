	@Test
	public void testSimpleInsert() {
		TestData data = new TestData();
		data.prepare();

		Session s = openSession();
		Transaction t = s.beginTransaction();

		s.createQuery( "insert into Pickup (id, vin, owner) select id, vin, owner from Car" ).executeUpdate();

		t.commit();
		t = s.beginTransaction();

		s.createQuery( "delete Vehicle" ).executeUpdate();

		t.commit();
		s.close();

		data.cleanup();
	}
