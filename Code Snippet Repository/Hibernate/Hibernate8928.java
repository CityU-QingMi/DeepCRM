	@Test
	public void testCreateExceptionWithGeneratedId() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		NumberedNode dupe = new NumberedNode("dupe");
		s.persist(dupe);
		s.persist(dupe);
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		try {
			s.persist(dupe);
			assertFalse(true);
		}
		catch (PersistenceException e){
			//verify that an exception is thrown!
			assertTyping(PersistentObjectException.class, e.getCause());
		}
		tx.rollback();
		s.close();

		NumberedNode nondupe = new NumberedNode("nondupe");
		nondupe.addChild(dupe);

		s = openSession();
		tx = s.beginTransaction();
		try {
			s.persist(nondupe);
			assertFalse(true);
		}
		catch (PersistenceException e){
			//verify that an exception is thrown!
			assertTyping(PersistentObjectException.class, e.getCause());
		}
		tx.rollback();
		s.close();
	}
