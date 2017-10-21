	@Test
	public void testEqualityForThePropertyOfAComponent() {
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
									 CriteriaBuilder builder = entityManager.getCriteriaBuilder();
									 CriteriaQuery<Employee> query = builder.createQuery( Employee.class );
									 Root<Employee> root = query.from( Employee.class );

									 query.where(
											 builder.equal(
													 root.join( "contactDetail" ).get( "email" )
													 , "abc@mail.org"
											 )
									 );

									 final List<Employee> results = entityManager.createQuery( query ).getResultList();
									 assertThat( results.size(), is( 1 ) );
								 }
		);
	}
