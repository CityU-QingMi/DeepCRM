	@Test
	public void testSizeExpressionForTheElementCollectionPropertyOfAComponent() {
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
									 CriteriaBuilder builder = entityManager.getCriteriaBuilder();
									 CriteriaQuery<Employee> query = builder.createQuery( Employee.class );
									 Root<Employee> root = query.from( Employee.class );

									 query.where(
											 builder.equal(
													 builder.size( root.get( "contactDetail" ).get( "phones" ) )
													 , 1 )
									 );

									 final List<Employee> results = entityManager.createQuery( query ).getResultList();
									 assertThat( results.size(), is( 1 ) );
								 }
		);
	}
