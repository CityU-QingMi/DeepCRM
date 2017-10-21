	@Test
	public void testRoundFunction(){
		Product product = new Product();
		product.setLength( 100 );
		product.setPrice( new BigDecimal( 1.298 ) );
		Session s=openSession();
		Transaction tx=s.beginTransaction();
		s.save( product );
		tx.commit();
		s.close();
		s=openSession();
		tx=s.beginTransaction();
		Query q=s.createQuery( "select round(p.price,1) from Product p" );
		Object o=q.uniqueResult();
		assertEquals( BigDecimal.class , o.getClass() );
		assertEquals( BigDecimal.valueOf( 1.3 ) , o );
		tx.commit();
		s.close();
		
	}
