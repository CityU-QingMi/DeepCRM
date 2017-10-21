	@Test
	public void testTargetOnEmbedded() throws Exception {
		Session s = openSession();
		s.getTransaction().begin();
		Luggage l = new LuggageImpl();
		l.setHeight( 12 );
		l.setWidth( 12 );
		Owner o = new OwnerImpl();
		o.setName( "Emmanuel" );
		l.setOwner( o );
		s.persist( l );
		s.flush();
		s.clear();
		l = (Luggage) s.get(LuggageImpl.class, ( (LuggageImpl) l).getId() );
		assertEquals( "Emmanuel", l.getOwner().getName() );
		s.getTransaction().rollback();
		s.close();
	}
