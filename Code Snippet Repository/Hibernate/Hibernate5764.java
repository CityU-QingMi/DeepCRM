	@Test
	public void selectCaseWithValuesShouldWork() {
		EntityManager entityManager = getOrCreateEntityManager();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaBuilder.Case<EnumValue> selectCase = cb.selectCase();
		Predicate somePredicate = cb.equal( cb.literal( 1 ), 1 );
		selectCase.when( somePredicate, EnumValue.VALUE_1 );
		selectCase.otherwise( EnumValue.VALUE_2 );

		CriteriaQuery<Entity> query = cb.createQuery( Entity.class );
		Root<Entity> from = query.from( Entity.class );
		query.select( from ).where( cb.equal( from.get( "value" ), selectCase ) );

		entityManager.createQuery( query ).getResultList();
	}
