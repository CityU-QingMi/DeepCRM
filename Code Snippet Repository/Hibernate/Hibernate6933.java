	@Test
	public void testEntitySQLOverriding() {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Chaos chaos = new Chaos();
		chaos.setSize( 123l );
		chaos.setId( 1l );

		String lowerName = "hello";
		String upperName = lowerName.toUpperCase(Locale.ROOT);
		assertFalse( lowerName.equals( upperName ) );

		chaos.setName( "hello" );
		chaos.setNickname( "NickName" );
		s.persist( chaos );
		s.flush();
		s.clear();
		s.getSessionFactory().getCache().evictEntityRegion( Chaos.class );

		Chaos resultChaos = s.load( Chaos.class, chaos.getId() );
		assertEquals( upperName, resultChaos.getName() );
		assertEquals( "nickname", resultChaos.getNickname() );

		tx.rollback();
		s.close();
	}
