	@Test
	public void testCollectionSQLOverriding() {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Chaos chaos = new Chaos();
		chaos.setSize( 123l );
		chaos.setId( 1l );

		chaos.setName( "hello" );
		s.persist( chaos );
		CasimirParticle p = new CasimirParticle();
		p.setId( 1l );
		s.persist( p );
		chaos.getParticles().add( p );
		p = new CasimirParticle();
		p.setId( 2l );
		s.persist( p );
		chaos.getParticles().add( p );
		s.flush();
		s.clear();
		s.getSessionFactory().getCache().evictEntityRegion( Chaos.class );

		Chaos resultChaos = s.load( Chaos.class, chaos.getId() );
		assertEquals( 2, resultChaos.getParticles().size() );
		resultChaos.getParticles().remove( resultChaos.getParticles().iterator().next() );
		resultChaos.getParticles().remove( resultChaos.getParticles().iterator().next() );
		s.flush();

		s.clear();
		resultChaos = s.load( Chaos.class, chaos.getId() );
		assertEquals( 0, resultChaos.getParticles().size() );

		tx.rollback();
		s.close();
	}
