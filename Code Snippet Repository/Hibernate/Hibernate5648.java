	@Test
	public void testTreatJoin() {
		EntityManager em = createEntityManager();
		try {
			final CriteriaBuilder cb = em.getCriteriaBuilder();

			final CriteriaQuery<Tuple> query = cb.createTupleQuery();
			final Root<TestEntity> testEntity = query.from( TestEntity.class );

			final List<Selection<?>> selections = new LinkedList();
			selections.add( testEntity.get( "id" ) );

			final ListJoin<TestEntity, AbstractEntity> entities = testEntity.joinList(
					"entities",
					JoinType.LEFT
			);
			entities.on( cb.equal( entities.get( "entityType" ), EntityA.class.getName() ) );

			final ListJoin<TestEntity, EntityA> joinEntityA = cb.treat(
					entities,
					EntityA.class
			);
			selections.add( joinEntityA.get( "id" ) );
			selections.add( joinEntityA.get( "valueA" ) );

			final ListJoin<TestEntity, AbstractEntity> entitiesB = testEntity.joinList(
					"entities",
					JoinType.LEFT
			);
			entitiesB.on( cb.equal( entitiesB.get( "entityType" ), EntityB.class.getName() ) );
			final ListJoin<TestEntity, EntityB> joinEntityB = cb.treat(
					entitiesB,
					EntityB.class
			);
			selections.add( joinEntityB.get( "id" ) );
			selections.add( joinEntityB.get( "valueB" ) );

			query.multiselect( selections );

			final List<Tuple> resultList = em.createQuery( query ).getResultList();
			assertThat( resultList.size(), is( 10 ) );
		}
		finally {
			em.close();
		}
	}
