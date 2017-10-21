	@Test
	public void testMapKeyOnEmbeddedId() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Generation c = new Generation();
		c.setAge( "a" );
		c.setCulture( "b" );
		c.setSubGeneration( new Generation.SubGeneration( "description" ) );
		GenerationGroup r = new GenerationGroup();
		r.setGeneration( c );
		s.persist( r );
		GenerationUser m = new GenerationUser();
		s.persist( m );
		m.getRef().put( c, r );
		s.flush();
		s.clear();

		m = (GenerationUser) s.get( GenerationUser.class, m.getId() );
		Generation cRead =  m.getRef().keySet().iterator().next();
		assertEquals( "a",cRead.getAge() );
		assertEquals( "description", cRead.getSubGeneration().getDescription() );
		tx.rollback();
		s.close();
	}
