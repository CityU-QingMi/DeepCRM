	@Test
	public void selectCaseWithCastedTypeValuesShouldWork() {
		EntityManager entityManager = getOrCreateEntityManager();

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaBuilder.Case<String> selectCase = cb.selectCase();
		Predicate somePredicate = cb.equal( cb.literal( 1 ), 1 );
		selectCase.when( somePredicate, EnumValue.VALUE_1.name() );
		selectCase.otherwise( EnumValue.VALUE_2.name() );

		CriteriaQuery<Entity> query = cb.createQuery( Entity.class );
		Root<Entity> from = query.from( Entity.class );
		query.select( from ).where( cb.equal( from.get( "value" ).as( String.class ), selectCase.as( String.class ) ) );

		entityManager.createQuery( query ).getResultList();
	}
