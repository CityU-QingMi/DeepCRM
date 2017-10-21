	@Test
	public void test_hql_api_basic_usage_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			//tag::hql-api-basic-usage-example[]
			org.hibernate.query.Query query = session.createQuery(
				"select p " +
				"from Person p " +
				"where p.name like :name" )
			// timeout - in seconds
			.setTimeout( 2 )
			// write to L2 caches, but do not read from them
			.setCacheMode( CacheMode.REFRESH )
			// assuming query cache was enabled for the SessionFactory
			.setCacheable( true )
			// add a comment to the generated SQL if enabled via the hibernate.use_sql_comments configuration property
			.setComment( "+ INDEX(p idx_person_name)" );
			//end::hql-api-basic-usage-example[]
		});
	}
