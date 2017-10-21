	@Test
	public void testDirectIdPropertyAccess() throws Exception {
		Session s = openSession();
		Transaction transaction = s.beginTransaction();
		QuotedIdentifier o = new QuotedIdentifier();
		o.timestamp = System.currentTimeMillis();
		o.from = "HHH-9271";
		s.persist( o );
		transaction.commit();
		s.close();

		s = openSession();
		transaction = s.beginTransaction();
		o = session.get( QuotedIdentifier.class, o.index );
		assertNotNull(o);
		transaction.commit();
		s.close();
	}
