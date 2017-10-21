	public void testUnidirectionalOneToMany() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();

		Clothes clothes = new Clothes( "underwear", "interesting" );
		Luggage luggage = new Luggage( "Emmanuel", "Cabin Luggage" );
		luggage.getHasInside().add( clothes );
		s.persist( luggage );

		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();

		luggage = (Luggage) s.createQuery( "select l from Luggage l left join fetch l.hasInside" ).uniqueResult();
		assertNotNull( luggage );
		assertNotNull( luggage.getHasInside() );
		assertEquals( 1, luggage.getHasInside().size() );

		s.delete( luggage.getHasInside().iterator().next() );
		s.delete( luggage );

		tx.commit();
		s.close();
	}
