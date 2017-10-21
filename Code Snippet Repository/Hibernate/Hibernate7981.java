	@Test
    public void testInsertWithMultipleNamedParams() {
		TestData data = new TestData();
		data.prepare();

		Session s = openSession();
		Transaction t = s.beginTransaction();

		org.hibernate.Query q = s.createQuery( "insert into Pickup (id, owner, vin) select :id, owner, :vin from Car" );
		q.setParameter("id", 5l);
        q.setParameter("vin", "some");

		q.executeUpdate();

		t.commit();
		t = s.beginTransaction();

		s.createQuery( "delete Vehicle" ).executeUpdate();

		t.commit();
		s.close();

		data.cleanup();
	}
