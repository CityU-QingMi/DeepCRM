	@Test
	public void testEmbeddableUsesAccessStrategyOfContainingClass() throws Exception {
		Circle circle = new Circle();
		Color color = new Color( 5, 10, 15 );
		circle.setColor( color );
		Session s = openSession();
		s.persist( circle );
		Transaction tx = s.beginTransaction();
		tx.commit();
		s.clear();
		tx = s.beginTransaction();
		circle = ( Circle ) s.get( Circle.class, circle.getId() );
		assertEquals( 5, circle.getColor().r );
		try {
			circle.getColor().getR();
			fail();
		} catch (RuntimeException e) {
			// success		
		}
		s.delete( circle );
		tx.commit();
		s.close();
	}
