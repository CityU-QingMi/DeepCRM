	@Test
	public void testInsertWithManyToOne() {
		TestData data = new TestData();
		data.prepare();

		Session s = openSession();
		Transaction t = s.beginTransaction();

		s.createQuery( "insert into Animal (description, bodyWeight, mother) select description, bodyWeight, mother from Human" ).executeUpdate();

		t.commit();
		t = s.beginTransaction();

		t.commit();
		s.close();

		data.cleanup();
	}
