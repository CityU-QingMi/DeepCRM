	@Test
	public void test() {

		//tag::basic-custom-type-BitSetType-persistence-example[]
		BitSet bitSet = BitSet.valueOf( new long[] {1, 2, 3} );

		doInHibernate( this::sessionFactory, session -> {
			Product product = new Product( );
			product.setId( 1 );
			product.setBitSet( bitSet );
			session.persist( product );
		} );

		doInHibernate( this::sessionFactory, session -> {
			Product product = session.get( Product.class, 1 );
			assertEquals(bitSet, product.getBitSet());
		} );
		//end::basic-custom-type-BitSetType-persistence-example[]
	}
