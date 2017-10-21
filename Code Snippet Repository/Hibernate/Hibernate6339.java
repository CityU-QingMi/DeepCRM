	@Test
	public void testDetach() {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Tooth tooth = new Tooth();
		Mouth mouth = new Mouth();
		s.persist( mouth );
		s.persist( tooth );
		tooth.mouth = mouth;
		mouth.teeth = new ArrayList<Tooth>();
		mouth.teeth.add( tooth );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		mouth = (Mouth) s.get( Mouth.class, mouth.id );
		assertNotNull( mouth );
		assertEquals( 1, mouth.teeth.size() );
		tooth = mouth.teeth.iterator().next();
		s.evict( mouth );
		assertFalse( s.contains( tooth ) );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		s.delete( s.get( Mouth.class, mouth.id ) );
		
		tx.commit();
		s.close();
	}
