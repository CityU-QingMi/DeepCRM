	@Test
    public void testSimpleInsertWithNamedParam() {
		TestData data = new TestData();
		data.prepare();

		Session s = openSession();
		Transaction t = s.beginTransaction();

		org.hibernate.Query q = s.createQuery( "insert into Pickup (id, owner, vin) select id, :owner, vin from Car" );
		q.setParameter("owner", "owner");

		q.executeUpdate();

		t.commit();
		t = s.beginTransaction();

		s.createQuery( "delete Vehicle" ).executeUpdate();

		t.commit();
		s.close();

		data.cleanup();
	}
