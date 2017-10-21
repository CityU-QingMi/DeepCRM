	@Test
	public void testManyToManyFilterOnQuery() {
		TestData testData = new TestData();
		testData.prepare();

		Session session = openSession();
		session.enableFilter( "effectiveDate" ).setParameter( "asOfDate", new Date() );

		List result = session.createQuery( "from Product p inner join fetch p.categories" ).list();
		assertTrue( "No products returned from HQL many-to-many filter case", !result.isEmpty() );

		Product prod = ( Product ) result.get( 0 );

		assertNotNull( prod );
		assertEquals( "Incorrect Product.categories count for filter with HQL", 1, prod.getCategories().size() );

		session.close();
		testData.release();
	}
