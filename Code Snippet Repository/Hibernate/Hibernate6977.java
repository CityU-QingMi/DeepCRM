	@Test
	public void testTargetOnMapKey() throws Exception {
		Session s = openSession();
		s.getTransaction().begin();
		Luggage l = new LuggageImpl();
		l.setHeight( 12 );
		l.setWidth( 12 );
		Size size = new SizeImpl();
		size.setName( "S" );
		Owner o = new OwnerImpl();
		o.setName( "Emmanuel" );
		l.setOwner( o );
		s.persist( l );
		Brand b = new Brand();
		s.persist( b );
		b.getLuggagesBySize().put( size, l );
		s.flush();
		s.clear();
		b = (Brand) s.get(Brand.class, b.getId() );
		assertEquals( "S", b.getLuggagesBySize().keySet().iterator().next().getName() );
		s.getTransaction().rollback();
		s.close();
	}
