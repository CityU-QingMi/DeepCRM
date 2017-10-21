	@Test
	@TestForIssue(jiraKey = "")
	public void testTupleStream() {
		doInHibernate( this::sessionFactory, session -> {
			MyEntity entity = new MyEntity();
			entity.id = 2;
			entity.name = "an entity";
			session.persist( entity );
		} );

		//test tuple stream using criteria
		doInHibernate( this::sessionFactory, session -> {
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteria = cb.createTupleQuery();
			Root<MyEntity> me = criteria.from( MyEntity.class );
			criteria.multiselect( me.get( "id" ), me.get( "name" ) );
			Stream<Tuple> data = session.createQuery( criteria ).stream();
			data.forEach( tuple -> assertTyping( Tuple.class, tuple ) );
		} );

		//test tuple stream using JPQL
		doInHibernate( this::sessionFactory, session -> {
			Stream<Tuple> data = session.createQuery( "SELECT me.id, me.name FROM MyEntity me", Tuple.class ).stream();
			data.forEach( tuple -> assertTyping( Tuple.class, tuple ) );
		} );
	}
