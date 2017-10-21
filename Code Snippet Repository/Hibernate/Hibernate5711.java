	@Test
	public void testSizeExpressionForTheElementCollectionPropertyOfAComponent() {
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
									 CriteriaBuilder builder = entityManager.getCriteriaBuilder();
									 CriteriaQuery<Manager> query = builder.createQuery( Manager.class );
									 Root<Manager> root = query.from( Manager.class );

									 query.where(
											 builder.equal(
													 builder.size( root.get( "contactDetail" ).get( "phones" ) )
													 , 1 )
									 );

									 final List<Manager> results = entityManager.createQuery( query ).getResultList();
									 assertThat( results.size(), is( 1 ) );
								 }
		);
	}
