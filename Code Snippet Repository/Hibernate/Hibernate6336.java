	@Test
	public void testPersist() {
		Session s;
		Transaction tx;
		s = openSession();
		Tooth tooth = new Tooth();
		Tooth leftTooth = new Tooth();
		tooth.leftNeighbour = leftTooth;
		s.persist( tooth );
		tx = s.beginTransaction();
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		leftTooth = (Tooth) s.get( Tooth.class, leftTooth.id );
		assertNotNull( leftTooth );
		tx.commit();
		s.close();
	}
