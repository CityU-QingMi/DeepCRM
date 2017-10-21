	@Test
	public void testSizeExpressionForTheElementCollectionPropertyOfASubComponent() {
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
					CriteriaBuilder builder = entityManager.getCriteriaBuilder();
					CriteriaQuery<Person> query = builder.createQuery( Person.class );
					Root<Person> root = query.from( Person.class );

					query.where(
							builder.equal(
									builder.size( root.get( "information" ).get( "infoContactDetail" ).get( "phones" ) )
									, 1 )
					);

					final List<Person> results = entityManager.createQuery( query ).getResultList();
					assertThat( results.size(), is( 1 ) );
				}
		);
	}
