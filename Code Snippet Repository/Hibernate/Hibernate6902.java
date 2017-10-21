	@Test
	@TestForIssue( jiraKey = "")
	public void testNotFoundBidirDefaultIdGenerator() {
		Session s = openSession();
		s.getTransaction().begin();
		Owner owner = new Owner();
		owner.setAddress( null );
		s.persist( owner );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		owner = ( Owner ) s.get( Owner.class, owner.getId() );
		assertNotNull( owner );
		assertNull( owner.getAddress() );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		owner = ( Owner ) s.createCriteria( Owner.class )
				.add( Restrictions.idEq( owner.getId() ) )
				.uniqueResult();
		assertNotNull( owner );
		assertNull( owner.getAddress() );
		s.delete( owner );
		s.getTransaction().commit();
		s.close();
	}
