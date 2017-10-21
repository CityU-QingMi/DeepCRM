	@Before
	public void fillData() {
		final Session s = openSession();
		s.getTransaction().begin();

		final Item i0 = new Item();
		i0.setPrice( new BigDecimal( "12.05" ) );
		i0.setQuantity( 10 );
		s.persist( i0 );

		final Item i1 = new Item();
		i1.setPrice( new BigDecimal( "5.35" ) );
		i1.setQuantity( 5 );
		s.persist( i1 );

		final Item i2 = new Item();
		i2.setPrice( new BigDecimal( "99.99" ) );
		i2.setQuantity( 15 );
		s.persist( i2 );

		s.getTransaction().commit();
		s.close();
	}
