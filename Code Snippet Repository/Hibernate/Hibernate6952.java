	@Test
	public void testOneToMany() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();

		Rambler rambler = new Rambler( "Emmanuel" );
		Bag bag = new Bag( "0001", rambler );
		rambler.getBags().add( bag );
		s.persist( rambler );

		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();

		bag = (Bag) s.createQuery( "select b from Bag b left join fetch b.owner" ).uniqueResult();
		assertNotNull( bag );
		assertNotNull( bag.getOwner() );

		rambler = (Rambler) s.createQuery( "select r from Rambler r left join fetch r.bags" ).uniqueResult();
		assertNotNull( rambler );
		assertNotNull( rambler.getBags() );
		assertEquals( 1, rambler.getBags().size() );
		s.delete( rambler.getBags().iterator().next() );
		s.delete( rambler );

		tx.commit();
		s.close();
	}
