	@Test
	public void testNativeQuery() {
		BitSet bitSet = BitSet.valueOf( new long[] {1, 2, 3} );

		doInHibernate( this::sessionFactory, session -> {
			Product product = new Product( );
			product.setId( 1 );
			product.setBitSet( bitSet );
			session.persist( product );
		} );

		doInHibernate( this::sessionFactory, session -> {
			Product product = (Product) session.getNamedNativeQuery(
					"find_person_by_bitset")
					.setParameter( "id", 1L)
					.getSingleResult();

			assertEquals(bitSet, product.getBitSet());
		} );
	}
