	@Test
	public void testPartialComponentGeneration() {
		ComponentOwner owner = new ComponentOwner( "initial" );
		Session s = openSession();
		s.beginTransaction();
		s.save( owner );
		s.getTransaction().commit();
		s.close();

		assertNotNull( "expecting insert value generation", owner.getComponent() );
		int previousValue = owner.getComponent().getGenerated();
		assertFalse( "expecting insert value generation", 0 == previousValue );

		s = openSession();
		s.beginTransaction();
		owner = ( ComponentOwner ) s.get( ComponentOwner.class, owner.getId() );
		assertEquals( "expecting insert value generation", previousValue, owner.getComponent().getGenerated() );
		owner.setName( "subsequent" );
		s.getTransaction().commit();
		s.close();

		assertNotNull( owner.getComponent() );
		previousValue = owner.getComponent().getGenerated();

		s = openSession();
		s.beginTransaction();
		owner = ( ComponentOwner ) s.get( ComponentOwner.class, owner.getId() );
		assertEquals( "expecting update value generation", previousValue, owner.getComponent().getGenerated() );
		s.delete( owner );
		s.getTransaction().commit();
		s.close();
	}
