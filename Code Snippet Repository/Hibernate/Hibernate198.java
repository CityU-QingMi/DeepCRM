	@Test
	public void test_hql_api_scroll_projection_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			//tag::hql-api-scroll-example[]
			try ( ScrollableResults scrollableResults = session.createQuery(
					"select p " +
					"from Person p " +
					"where p.name like :name" )
					.setParameter( "name", "J%" )
					.scroll()
			) {
				while(scrollableResults.next()) {
					Person person = (Person) scrollableResults.get()[0];
					process(person);
				}
			}
			//end::hql-api-scroll-example[]
		});
	}
