	@Test
	public void testSizeExpressionForTheElementCollectionPropertyOfASubComponent() {
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
					CriteriaBuilder builder = entityManager.getCriteriaBuilder();
					CriteriaQuery<Leader> query = builder.createQuery( Leader.class );
					Root<Leader> root = query.from( Leader.class );

					query.where(
							builder.equal(
									builder.size( root.get( "information" ).get( "infoContactDetail" ).get( "phones" ) )
									, 1 )
					);

					final List<Leader> results = entityManager.createQuery( query ).getResultList();
					assertThat( results.size(), is( 1 ) );
				}
		);
	}
