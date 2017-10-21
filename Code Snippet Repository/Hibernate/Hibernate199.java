	@Test
	public void test_hql_api_scroll_open_example() {
		ScrollableResults scrollableResults = doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			return session.createQuery(
				"select p " +
				"from Person p " +
				"where p.name like :name" )
			.setParameter( "name", "J%" )
			.scroll();
		});
		try {
			scrollableResults.next();
			fail("Should throw exception because the ResultSet must be closed by now!");
		}
		catch ( Exception expected ) {
		}
	}
