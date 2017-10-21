	@Test
	public void testUnidirectionalTrueOneToOne() throws Exception {
		Body b = new Body();
		Heart h = new Heart();
		b.setHeart( h );
		b.setId( 1 );
		h.setId( b.getId() ); //same PK
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		s.persist( h );
		s.persist( b );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		b = ( Body ) s.get( Body.class, b.getId() );
		assertNotNull( b );
		assertNotNull( b.getHeart() );
		assertEquals( h.getId(), b.getHeart().getId() );
		tx.commit();
		s.close();
	}
