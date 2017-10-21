	private void doTest(JoinBuilder joinBuilder) {
		EntityManager entityManager = getOrCreateEntityManager();
		entityManager.getTransaction().begin();

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteriaQuery = builder.createTupleQuery();
		Root<Entity> root = criteriaQuery.from( Entity.class );
		Join<Entity, EmbeddedType> join = root.join( "embeddedType", JoinType.LEFT );

		// left join to the manyToOne on the embeddable with a string property
		Path<String> path = joinBuilder.buildJoinToManyToOneType( join ).get( "value" );

		// select the path in the tuple
		criteriaQuery.select( builder.tuple( path ) );

		List<Tuple> results = entityManager.createQuery( criteriaQuery ).getResultList();
		Tuple result = results.iterator().next();

		assertEquals( THEVALUE, result.get(0) );

		entityManager.getTransaction().commit();
		entityManager.close();
	}
