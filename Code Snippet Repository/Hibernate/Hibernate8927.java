	@Test
	public void testCreateException() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Node dupe = new Node("dupe");
		s.persist(dupe);
		s.persist(dupe);
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		s.persist(dupe);
		try {
			tx.commit();
			fail( "Expecting constraint failure" );
		}
		catch (PersistenceException e){
			//verify that an exception is thrown!
			assertTyping(ConstraintViolationException.class, e.getCause());
		}
		tx.rollback();
		s.close();

		Node nondupe = new Node("nondupe");
		nondupe.addChild(dupe);

		s = openSession();
		tx = s.beginTransaction();
		s.persist(nondupe);
		try {
			tx.commit();
			assertFalse(true);
		}
		catch (PersistenceException e){
			//verify that an exception is thrown!
			assertTyping(ConstraintViolationException.class, e.getCause());
		}
		tx.rollback();
		s.close();
	}
