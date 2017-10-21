	private CriteriaQuery<TestEntity> createTestEntityCriteriaQuery(
			String entityName,
			Session session) {
		final CriteriaBuilder builder = session.getCriteriaBuilder();
		final CriteriaQuery<TestEntity> query =
				builder.createQuery( TestEntity.class );
		final Root<TestEntity> fromTestEntity = query.from( TestEntity.class );
		query.select( fromTestEntity );
		query.where( builder.equal(
				fromTestEntity.get( "name" ),
				entityName
		) );
		return query;
	}
