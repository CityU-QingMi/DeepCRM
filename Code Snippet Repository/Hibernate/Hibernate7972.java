	@Test
	public void testStandardFunctions() throws Exception {
		Session session = openSession();
		Transaction t = session.beginTransaction();
		Product p = new Product();
		p.setDescription( "a product" );
		p.setPrice( new BigDecimal( 1.0 ) );
		p.setProductId( "abc123" );
		session.persist(p);
		Object[] result = (Object[]) session
			.createQuery("select current_time(), current_date(), current_timestamp() from Product")
			.uniqueResult();
		assertTrue( result[0] instanceof Time );
		assertTrue( result[1] instanceof Date );
		assertTrue( result[2] instanceof Timestamp );
		assertNotNull( result[0] );
		assertNotNull( result[1] );
		assertNotNull( result[2] );
		session.delete(p);
		t.commit();
		session.close();
	}
