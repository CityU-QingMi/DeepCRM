	@Test
	public void testBetweenParameters() {
		final Session s = openSession();
		s.getTransaction().begin();

		final Query query = s.createQuery( "from Item where quantity between :low and :high" );
		query.setParameter( "low", new Integer( 9 ) );
		query.setParameter( "high", new Integer( 11 ) );
		@SuppressWarnings("unchecked")
		final List<Item> result = query.list();
		assertEquals( 1, result.size() );
		assertEquals( 10, result.get( 0 ).getQuantity().intValue() );

		s.getTransaction().commit();
		s.close();
	}
