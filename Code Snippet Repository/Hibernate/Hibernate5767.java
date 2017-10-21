	@Test
	public void simpleSelectCaseWithCastedTypeValuesShouldWork() {
		EntityManager entityManager = getOrCreateEntityManager();

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaBuilder.SimpleCase<Integer, String> selectCase = cb.selectCase( cb.literal( 1 ) );
		selectCase.when( 1, EnumValue.VALUE_1.name() );
		selectCase.otherwise( EnumValue.VALUE_2.name() );

		CriteriaQuery<Entity> query = cb.createQuery( Entity.class );
		Root<Entity> from = query.from( Entity.class );
		query.select( from ).where( cb.equal( from.get( "value" ).as( String.class ), selectCase.as( String.class ) ) );

		entityManager.createQuery( query ).getResultList();
	}
