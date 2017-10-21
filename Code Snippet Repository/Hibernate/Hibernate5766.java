	@Test
	public void simpleSelectCaseWithValuesShouldWork() {
		EntityManager entityManager = getOrCreateEntityManager();

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaBuilder.SimpleCase<Integer, EnumValue> selectCase = cb.selectCase( cb.literal( 1 ) );
		selectCase.when( 1, EnumValue.VALUE_1 );
		selectCase.otherwise( EnumValue.VALUE_2 );

		CriteriaQuery<Entity> query = cb.createQuery( Entity.class );
		Root<Entity> from = query.from( Entity.class );
		query.select( from ).where( cb.equal( from.get( "value" ), selectCase ) );

		List<?> result = entityManager.createQuery( query ).getResultList();
	}
