	@Test
	@TestForIssue(jiraKey = "")
	public void testPaginationWithAggregation() {
		doInHibernate( this::sessionFactory, session -> {
			// populating test data
			Category category1 = new Category( 1, "Category1" );
			Category category2 = new Category( 2, "Category2" );
			Category category3 = new Category( 3, "Category3" );
			session.persist( category1 );
			session.persist( category2 );
			session.persist( category3 );
			session.flush();
			session.persist( new Product2( 1, "Kit1", category1 ) );
			session.persist( new Product2( 2, "Kit2", category1 ) );
			session.persist( new Product2( 3, "Kit3", category1 ) );
			session.persist( new Product2( 4, "Kit4", category2 ) );
			session.persist( new Product2( 5, "Kit5", category2 ) );
			session.persist( new Product2( 6, "Kit6", category3 ) );
			session.flush();
			session.clear();

			// count number of products in each category
			List<Object[]> result = session.createCriteria( Category.class, "c" ).createAlias( "products", "p" )
					.setProjection(
							Projections.projectionList()
									.add( Projections.groupProperty( "c.id" ) )
									.add( Projections.countDistinct( "p.id" ) )
					)
					.addOrder( Order.asc( "c.id" ) )
					.setFirstResult( 1 ).setMaxResults( 3 ).list();

			assertEquals( 2, result.size() );
			assertArrayEquals( new Object[] { 2, 2L }, result.get( 0 ) ); // two products of second category
			assertArrayEquals( new Object[] { 3, 1L }, result.get( 1 ) ); // one products of third category
		} );
	}
