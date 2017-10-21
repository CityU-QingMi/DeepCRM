	@Test
	public void testTargetOnMapKeyManyToMany() throws Exception {
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
		b.getSizePerLuggage().put( l, size );
		s.flush();
		s.clear();
		b = (Brand) s.get(Brand.class, b.getId() );
		assertEquals( 12d, b.getSizePerLuggage().keySet().iterator().next().getWidth(), 0.01 );
		s.getTransaction().rollback();
		s.close();
	}
