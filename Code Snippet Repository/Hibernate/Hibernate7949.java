	public void testImplicitPolymorphism() {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		Product product = new Product();
		product.setDescription( "My Product" );
		product.setNumberAvailable( 10 );
		product.setPrice( new BigDecimal( 123 ) );
		product.setProductId( "4321" );
		s.save( product );

		List list = s.createQuery("from java.lang.Comparable").list();
		assertEquals( list.size(), 0 );

		list = s.createQuery("from java.lang.Object").list();
		assertEquals( list.size(), 1 );

		s.delete(product);

		list = s.createQuery("from java.lang.Object").list();
		assertEquals( list.size(), 0 );

		t.commit();
		s.close();
	}
