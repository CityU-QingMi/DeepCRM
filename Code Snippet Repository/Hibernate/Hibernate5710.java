	@Test
	public void testSizeExpressionForTheOneToManyPropertyOfAComponent() {
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
									 CriteriaBuilder builder = entityManager.getCriteriaBuilder();
									 CriteriaQuery<Manager> query = builder.createQuery( Manager.class );
									 Root<Manager> root = query.from( Manager.class );

									 query.where(
											 builder.equal(
													 builder.size( root.get( "projects" ).get( "previousProjects" ) )
													 , 2 ) );

									 final List<Manager> results = entityManager.createQuery( query ).getResultList();
									 assertThat( results.size(), is( 1 ) );
								 }
		);
	}
