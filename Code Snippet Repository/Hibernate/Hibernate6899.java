	@Test
	public void testBidirDefaultIdGenerator() throws Exception {
		Session s = openSession();
		s.getTransaction().begin();
		PersonAddress personAddress = new PersonAddress();
		personAddress.setPerson( null );

		s.persist( personAddress );
		s.getTransaction().commit();

		s.clear();

		Transaction tx = s.beginTransaction();

		personAddress = ( PersonAddress ) s.createCriteria(PersonAddress.class)
				.add( Restrictions.idEq( personAddress.getId() ) )
				.uniqueResult();
		assertNotNull( personAddress );
		assertNull( personAddress.getPerson() );

		s.clear();

		personAddress = ( PersonAddress ) s.get( PersonAddress.class, personAddress.getId() );
		assertNull( personAddress.getPerson() );

		s.delete( personAddress );
		tx.commit();
		s.close();
	}
