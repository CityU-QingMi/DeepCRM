	@Test
	public void testStaleNonVersionedInstanceFoundInQueryResult() {
		String check = "Lock Modes";
		Session s1 = sessionFactory().openSession();
		Transaction t1 = s1.beginTransaction();
		Part part = new Part( new Item( "EJB3 Specification" ), check, "3.3.5.3", new BigDecimal( 0.0 ) );
		s1.save( part );
		t1.commit();
		s1.close();

		Long partId = part.getId();

		// Now, open a new Session and re-load the part...
		s1 = sessionFactory().openSession();
		t1 = s1.beginTransaction();
		part = ( Part ) s1.get( Part.class, partId );

		// now that the item is associated with the persistence-context of that session,
		// open a new session and modify it "behind the back" of the first session
		Session s2 = sessionFactory().openSession();
		Transaction t2 = s2.beginTransaction();
		Part part2 = ( Part ) s2.get( Part.class, partId );
		part2.setName( "Lock Mode Types" );
		t2.commit();
		s2.close();

		// at this point, s1 now contains stale data, so try an hql query which
		// returns said part and make sure we get the previously associated state
		// (i.e., the old name)
		part2 = ( Part ) s1.createQuery( "select p from Part p" ).list().get( 0 );
		assertTrue( part == part2 );
		assertEquals( "encountered non-repeatable read", check, part2.getName() );

		t1.commit();
		s1.close();

		// clean up
		s1 = sessionFactory().openSession();
		t1 = s1.beginTransaction();
		s1.delete( part2 );
		s1.delete( part2.getItem() );
		t1.commit();
		s1.close();
	}
