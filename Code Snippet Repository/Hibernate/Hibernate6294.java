	@Test
	public void testEmbeddableExplicitAccessStrategy() throws Exception {
		Square square = new Square();
		Position pos = new Position( 10, 15 );
		square.setPosition( pos );
		Session s = openSession();
		s.persist( square );
		Transaction tx = s.beginTransaction();
		tx.commit();
		s.clear();
		tx = s.beginTransaction();
		square = ( Square ) s.get( Square.class, square.getId() );
		assertEquals( 10, square.getPosition().x );
		try {
			square.getPosition().getX();
			fail();
		} catch (RuntimeException e) {
			// success
		}
		s.delete( square );
		tx.commit();
		s.close();
	}
