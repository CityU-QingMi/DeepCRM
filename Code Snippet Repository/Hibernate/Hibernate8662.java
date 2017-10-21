	@Test
	public void testNothinToUpdate() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Simple simple = new Simple( Long.valueOf(10) );
		simple.setName("Simple 1");
		s.save( simple );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		s.update( simple );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		s.update( simple );
		s.delete(simple);
		t.commit();
		s.close();
	}
