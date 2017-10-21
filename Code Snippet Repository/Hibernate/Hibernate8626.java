	@Test
	public void testConstraints() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		SubMulti sm = new SubMulti();
		sm.setAmount(66.5f);
		s.save( sm );
		t.commit();
		s.close();

		s = openSession();
//		doDelete( s, "from SubMulti" );
//		t = s.beginTransaction();
		t = s.beginTransaction();
		doDelete( s, "from SubMulti" );
		t.commit();
		s.close();
	}
