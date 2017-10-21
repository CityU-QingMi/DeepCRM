	@Test
	public void testSizeExpressionForTheOneToManyPropertyOfAComponent() {
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
									 CriteriaBuilder builder = entityManager.getCriteriaBuilder();
									 CriteriaQuery<Employee> query = builder.createQuery( Employee.class );
									 Root<Employee> root = query.from( Employee.class );

									 query.where(
											 builder.equal(
													 builder.size( root.get( "projects" ).get( "previousProjects" ) )
													 , 2 ) );

									 final List<Employee> results = entityManager.createQuery( query ).getResultList();
									 assertThat( results.size(), is( 1 ) );
								 }
		);
	}
