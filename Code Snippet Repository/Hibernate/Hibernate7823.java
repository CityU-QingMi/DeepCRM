	@Test
	public void testManyToManyFilterOnCriteria() {
		TestData testData = new TestData();
		testData.prepare();

		Session session = openSession();
		session.enableFilter( "effectiveDate" ).setParameter( "asOfDate", new Date() );

		Product prod = ( Product ) session.createCriteria( Product.class )
		        .setResultTransformer( DistinctRootEntityResultTransformer.INSTANCE )
		        .add( Restrictions.eq( "id", testData.prod1Id ) )
		        .uniqueResult();

		assertNotNull( prod );
		assertEquals( "Incorrect Product.categories count for filter", 1, prod.getCategories().size() );

		session.close();
		testData.release();
	}
