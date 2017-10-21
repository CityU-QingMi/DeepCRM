	@Test
	public void test_sql_hibernate_inheritance_query_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			//tag::sql-hibernate-inheritance-query-example[]
			List<CreditCardPayment> payments = session.createNativeQuery(
				"SELECT * " +
				"FROM Payment p " +
				"JOIN CreditCardPayment cp on cp.id = p.id" )
			.addEntity( CreditCardPayment.class )
			.list();
			//end::sql-hibernate-inheritance-query-example[]
			assertEquals(1, payments.size());
		});
	}
