	@Test
	public void testManyToOne() throws Exception {
		final Currency euro = new Currency();
		euro.setName( "Euro" );

		final Coin fiveCents = new Coin();
		fiveCents.setName( "Five cents" );
		fiveCents.setCurrency( euro );

		doInHibernate( this::sessionFactory, session -> {
			session.persist( euro );
			session.persist( fiveCents );
		} );

		doInHibernate( this::sessionFactory, session -> {
			Currency _euro = session.get( Currency.class, euro.getId() );
			session.delete( _euro );
		} );

		doInHibernate( this::sessionFactory, session -> {
			Coin _fiveCents = session.get( Coin.class, fiveCents.getId() );
			assertNull( _fiveCents.getCurrency() );
			session.delete( _fiveCents );
		} );
	}
